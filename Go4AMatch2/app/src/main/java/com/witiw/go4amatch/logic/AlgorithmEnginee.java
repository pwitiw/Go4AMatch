package com.witiw.go4amatch.logic;

import android.os.AsyncTask;
import android.util.Log;

import com.witiw.go4amatch.IMainPresenter;
import com.witiw.go4amatch.entities.Criterion;
import com.witiw.go4amatch.entities.LeagueType;
import com.witiw.go4amatch.entities.SportingEvent;
import com.witiw.go4amatch.logic.ahp.AHPAlgorithm;
import com.witiw.go4amatch.logic.ahp.InconsistentConsistentMatrixException;
import com.witiw.go4amatch.logic.classifier.BayesClassifier;
import com.witiw.go4amatch.logic.promethee.PrometheeAlgorithm;
import com.witiw.go4amatch.logic.services.FormService;
import com.witiw.go4amatch.rest.impl.GoogleRestService;
import com.witiw.go4amatch.rest.impl.SportRadarRestService;
import com.witiw.go4amatch.utils.AppProperties;
import com.witiw.go4amatch.utils.ListPrinter;

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
            testGooglePlaces();
            bayesClassifier = new BayesClassifier();
            events = run(Arrays.asList(params));
        } catch (Exception e) {
            if (e instanceof IllegalArgumentException)
                succesFlag = 2;
            else {
                succesFlag = 1;
            }
            e.printStackTrace();
        } catch (InconsistentConsistentMatrixException e) {
            succesFlag = 1;
        }
        return events;
    }

    private List<SportingEvent> processDataFromServer(LeagueType leagueType) throws Exception {
        return dataProcessingService.getSportingEventsForLeague(leagueType);
    }

    private List<SportingEvent> processDataFromXml() throws Exception {
        return dataProcessingService.processDataFromXmlFile(AppProperties.TEST_DATA_FILE_NAME);
    }

    public List<SportingEvent> run(List<Criterion> criteria) throws Exception, InconsistentConsistentMatrixException {

        ahpAlgorithm.compute(criteria);
        bayesClassifier = new BayesClassifier();
        // FROM SERVER
//        List<SportingEvent> events = processDataFromServer(LeagueType.UKRAINE);
        // FROM XML
//    todo normalnie    List<SportingEvent> events = processDataFromXml();
        //na rzecz testow
        List<SportingEvent> events = formTesting(dataProcessingService);
        List<SportingEvent> defaultEvents = new ArrayList<>(events);
        classifyAttractiveness(events);
        prometheeAlgorithm.calculateRanking(criteria, events);
        //todo normalnie return events;
        //na rzecz testów
        ListPrinter.printResults(defaultEvents, events);

        return events.subList(0, 5);
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

    private void testGooglePlaces() throws Exception {
        String city = mainPresenter.getLocation();
        if (city.equals(""))
            return;
        int result = GoogleRestService.getAttractionsForCity(city);
        Log.i(city, String.valueOf(result));
    }

    private List<SportingEvent> formTesting(DataProcessingService dataProcessingService) throws Exception {

        if (mainPresenter.getLocation().equals(""))
            return dataProcessingService.processDataFromXmlFile(AppProperties.SPORTING_EVENTS_LIST_1);
        else
            return dataProcessingService.processDataFromXmlFile(AppProperties.SPORTING_EVENTS_LIST_2);
    }

}
