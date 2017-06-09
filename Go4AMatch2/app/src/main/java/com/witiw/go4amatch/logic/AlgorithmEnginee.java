package com.witiw.go4amatch.logic;

import android.app.ProgressDialog;
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
import com.witiw.go4amatch.rest.api.sportradar.teaminfo.TeamProfile;
import com.witiw.go4amatch.rest.impl.GoogleRestService;
import com.witiw.go4amatch.rest.impl.SportRadarRestService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return events;
    }


    public List<SportingEvent> run(List<Criterion> criteria) throws Exception {
        ahpAlgorithm.compute(criteria);
        bayesClassifier = new BayesClassifier();
        List<SportingEvent> events = dataProcessingService.getSportingEventsForLeague(LeagueType.ENGLAND);
        Log.i("#CALL NUMBERS", String.valueOf(GoogleRestService.GOOGLE_REST_SERVICE_COUNTER + SportRadarRestService.SPORT_RADAR_REST_SERVICE_COUNTER));
        return events;
    }

    @Override
    protected void onPostExecute(List<SportingEvent> events) {
        super.onPostExecute(events);
        mainPresenter.showResults(events);
    }

    private void classifyAttractiveness(List<SportingEvent> events) throws Exception {
        for (int i = 0; i < events.size(); i++) {
            SportingEvent event = events.get(i);
            event.getAttractiveness().setFormType(formService.getFormType(event.getTeams().getFormHome(), event.getTeams().getFormAway()));
            event.getAttractiveness().setValue(bayesClassifier.classify(event.getAttractiveness()));
        }
    }


}
