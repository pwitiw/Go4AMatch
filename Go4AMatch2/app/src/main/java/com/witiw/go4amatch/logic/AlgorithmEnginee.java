package com.witiw.go4amatch.logic;

import android.content.Context;

import com.squareup.okhttp.ResponseBody;
import com.witiw.go4amatch.IMainPresenter;
import com.witiw.go4amatch.entities.SportingEvent;
import com.witiw.go4amatch.logic.ahp.AHPAlgorithm;
import com.witiw.go4amatch.logic.classifier.BayesClassifier;
import com.witiw.go4amatch.logic.classifier.Condition;
import com.witiw.go4amatch.entities.Criterion;
import com.witiw.go4amatch.logic.promethee.PrometheeAlgorithm;
import com.witiw.go4amatch.rest.api.GoogleAPI;
import com.witiw.go4amatch.rest.api.RestServiceFactory;
import com.witiw.go4amatch.rest.google.PlaceSearchResponse;
import com.witiw.go4amatch.utils.DataReader;
import com.witiw.go4amatch.utils.XmlPojoConverter;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Patryk on 25.05.2017.
 */

public class AlgorithmEnginee {

    AHPAlgorithm ahpAlgorithm;
    BayesClassifier bayesClassifier;
    PrometheeAlgorithm prometheeAlgorithm;
    IMainPresenter mainPresenter;


    public AlgorithmEnginee(IMainPresenter mainPresenter, Context context) {
        DataReader.setAppContext(context);
        this.mainPresenter = mainPresenter;
        ahpAlgorithm = new AHPAlgorithm();
        try {
            bayesClassifier = new BayesClassifier();
        } catch (Exception e) {
            throw new RuntimeException();
        }
        prometheeAlgorithm = new PrometheeAlgorithm();
    }

    public void run(List<Criterion> criteria) throws Exception {
        testRest();
//        Document testDataDoc = DataReader.readTestData();
//        if (testDataDoc == null || testDataDoc == null)
//            throw new RuntimeException();
//        ahpAlgorithm.compute(criteria);
//        List<SportingEvent> events = XmlPojoConverter.convert(testDataDoc);
//        classifyAttractiveness(events);
//        prometheeAlgorithm.calculateRanking(criteria, events);
//        mainPresenter.showResults(events);
    }

    private void classifyAttractiveness(List<SportingEvent> events) throws Exception {
        for (int i = 0; i < events.size(); i++) {
            SportingEvent event = events.get(i);
            event.getAttractiveness().setCondition(Condition.setDownConditionType(event.getTeams().getFormHome(), event.getTeams().getFormAway()));
            event.getAttractiveness().setValue(bayesClassifier.classify(event.getAttractiveness()));
        }
    }

    public void run() {

    }

    public void testRest() {
//"New+York"
        GoogleAPI http = RestServiceFactory.getGoogleAPI(GoogleAPI.class);
        http.getAttractionsForCity().enqueue(new Callback<PlaceSearchResponse>() {
            @Override
            public void onResponse(Response<PlaceSearchResponse> response, Retrofit retrofit) {
                if (response.code() == 200) {
                    System.out.print("bad request");
                } else {
                    response.body();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }


}
