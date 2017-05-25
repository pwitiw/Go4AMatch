package com.witiw.go4amatch;

import com.witiw.go4amatch.entities.SportingEvent;

import java.util.List;

/**
 * Created by Patryk on 25.05.2017.
 */
public interface IMainPresenter {


    void performSearching();

    void showResults(List<SportingEvent> events);
}
