package com.witiw.go4amatch;

import android.content.Context;

import com.witiw.go4amatch.entities.SportingEvent;
import com.witiw.go4amatch.entities.Criterion;

import java.util.List;

/**
 * Created by Patryk on 25.05.2017.
 */
public interface IMainPresenter {


    void performSearching(List<Criterion> criterias);

    void showResults(List<SportingEvent> events);

    Context getContext();

    void showProgress();

    void hideProgress();

    String getLocation();

    void showToast(String text);
}
