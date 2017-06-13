package com.witiw.go4amatch.logic;

import android.os.AsyncTask;
import android.util.Log;

import com.witiw.go4amatch.IMainPresenter;
import com.witiw.go4amatch.entities.Criterion;
import com.witiw.go4amatch.entities.LeagueType;
import com.witiw.go4amatch.entities.SportingEvent;
import com.witiw.go4amatch.logic.ahp.AHPAlgorithm;
import com.witiw.go4amatch.logic.classifier.BayesClassifier;
import com.witiw.go4amatch.logic.promethee.PrometheeAlgorithm;
import com.witiw.go4amatch.logic.services.FormService;
import com.witiw.go4amatch.rest.impl.GoogleRestService;
import com.witiw.go4amatch.rest.impl.SportRadarRestService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Patryk on 25.05.2017.
 */

public class AlgorithmEnginee extends AsyncTask<Criterion, Void, List<SportingEvent>> {

    AHPAlgorithm ahpAlgorithm;
    BayesClassifier bayesClassifier;
    PrometheeAlgorithm prometheeAlgorithm;
    IMainPresenter mainPresenter;
    DataProcessingService dataProcessingService;
    FormService formService;
    private int succesFlag = 0;

    public AlgorithmEnginee(IMainPresenter mainPresenter) {
        formService = new FormService();
        dataProcessingService = new DataProcessingService(mainPresenter);
        this.mainPresenter = mainPresenter;
        ahpAlgorithm = new AHPAlgorithm();
        prometheeAlgorithm = new PrometheeAlgorithm();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mainPresenter.showProgress();
    }

    @Override
    protected List<SportingEvent> doInBackground(Criterion... params) {
        List<SportingEvent> events = new ArrayList<>();
        try {
            bayesClassifier = new BayesClassifier();
            events = run(Arrays.asList(params));
            printResults(null, events, null);
//            events = prepareTestData(Arrays.asList(params));
        } catch (Exception e) {
            if (e instanceof IllegalArgumentException)
                succesFlag = 2;
            else {
                succesFlag = 1;
            }
            e.printStackTrace();
        }

        return events;
    }

    public List<SportingEvent> run(List<Criterion> criteria) throws Exception {
        ahpAlgorithm.compute(criteria);
        bayesClassifier = new BayesClassifier();
        // FROM SERVER
//        List<SportingEvent> events = dataProcessingService.getSportingEventsForLeague(LeagueType.UKRAINE);
        // FROM XML
        List<SportingEvent> events = dataProcessingService.processDataFromXmlFile();
        classifyAttractiveness(events);
        prometheeAlgorithm.calculateRanking(criteria, events);
        Log.i("#CALL NUMBERS", String.valueOf(GoogleRestService.GOOGLE_REST_SERVICE_COUNTER + SportRadarRestService.SPORT_RADAR_REST_SERVICE_COUNTER));
        return events;
    }

    @Override
    protected void onPostExecute(List<SportingEvent> events) {
        super.onPostExecute(events);

        if (succesFlag == 0) {
            mainPresenter.showResults(events);
            mainPresenter.showToast("Przetwarzanie zakończone pomyślnie");
        } else if (succesFlag == 2) {
            mainPresenter.showToast("Nie wprowadzono lokalizacji.");
        } else
            mainPresenter.showToast("Operacja nie powiodła się.");
        mainPresenter.hideProgress();
    }

    private void classifyAttractiveness(List<SportingEvent> events) throws Exception {
        for (int i = 0; i < events.size(); i++) {
            SportingEvent event = events.get(i);
            event.getAttractiveness().setFormType(formService.getFormType(event.getTeams().getFormHome(), event.getTeams().getFormAway()));
            event.getAttractiveness().setValue(bayesClassifier.classify(event.getAttractiveness()));
        }
    }

    public List<SportingEvent> prepareTestData(List<Criterion> criteria) throws Exception {
        LeagueType leagueType = LeagueType.PORTUGAL;
        bayesClassifier = new BayesClassifier();

        List<SportingEvent> events = dataProcessingService.getSportingEventsForLeague(leagueType);
        criteria = removeSpecificCriteria(criteria, leagueType);
        classifyAttractiveness(events);
        for (Criterion criterion : criteria) {
            ArrayList<Criterion> singleCriteriaList = new ArrayList<>();
            singleCriteriaList.add(criterion);
            // dla jednego kryterium AHP nie jest stosowane, dlatego przypisuje sie wagę kryterium 1.
            // ahpAlgorithm.compute(singleCriteriaList);
            criterion.setFactor(1.0);
            prometheeAlgorithm.calculateRanking(singleCriteriaList, events);
            printResults(criterion, events, leagueType);
        }
        return events;
    }

    private List<Criterion> removeSpecificCriteria(List<Criterion> criteria, LeagueType leagueType) {
        List<Criterion> newList = new ArrayList<>();
        for (Criterion criterion : criteria) {
            if (LeagueType.CHAMPIONS_LEAGUE != leagueType && LeagueType.EUROPE != leagueType)
                if (criterion.getName().equals("Budżet") || criterion.getName().equals("Rodzaj rozgrywek"))
                    continue;
            newList.add(criterion);
        }
        return newList;
    }

    private void printResults(Criterion criterion, List<SportingEvent> events, LeagueType leagueType) {
        StringBuilder logBuilder = new StringBuilder();
        for (int i = 0; i < events.size(); i++) {
            logBuilder.append(events.get(i).toString());
        }
        if (criterion == null || leagueType == null) {
            Log.i("Melduje: \t\t\n", "\n" + logBuilder.toString() + "\n");
            return;
        }
        Log.i("League:\t" + leagueType.getLeagueName() + "\tCriterion:\t" + criterion.getName() + "\n", "\n" + logBuilder.toString() + "\n");
    }
}
