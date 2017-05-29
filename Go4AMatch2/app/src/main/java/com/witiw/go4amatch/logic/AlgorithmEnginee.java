package com.witiw.go4amatch.logic;

import android.content.Context;

import com.squareup.okhttp.ResponseBody;
import com.witiw.go4amatch.IMainPresenter;
import com.witiw.go4amatch.entities.SportingEvent;
import com.witiw.go4amatch.logic.ahp.AHPAlgorithm;
import com.witiw.go4amatch.logic.classifier.BayesClassifier;
import com.witiw.go4amatch.logic.objects.Criterion;
import com.witiw.go4amatch.logic.promethee.PrometheeAlgorithm;
import com.witiw.go4amatch.rest.api.GoogleAPI;
import com.witiw.go4amatch.rest.api.SportRadarAPI;
import com.witiw.go4amatch.rest.api.RestServiceFactory;
import com.witiw.go4amatch.rest.sportradar.sdk.RootObject;
import com.witiw.go4amatch.utils.*;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Patryk on 25.05.2017.
 */

public class AlgorithmEnginee extends Thread {

    AHPAlgorithm ahpAlgorithm;
    BayesClassifier bayesClassifier;
    PrometheeAlgorithm prometheeAlgorithm;
    IMainPresenter mainPresenter;
    DataReader dataReader;

    public AlgorithmEnginee(IMainPresenter mainPresenter, Context context) {
        this.mainPresenter = mainPresenter;
        dataReader = new DataReader(context);
        ahpAlgorithm = new AHPAlgorithm();
        // prometheeAlgorithm = new PrometheeAlgorithm();
    }

    public void run(List<Criterion> criterias) throws InterruptedException {
        dataReader.readData("");
        mainPresenter.showResults(new ArrayList<SportingEvent>());
    }

    @Override
    public void run() {

    }

    public void testRest() {
//"New+York"
        GoogleAPI http = RestServiceFactory.getGoogleAPI(GoogleAPI.class);
        http.getAttractionsForCity().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
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
