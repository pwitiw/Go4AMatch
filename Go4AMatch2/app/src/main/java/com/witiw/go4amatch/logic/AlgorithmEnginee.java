package com.witiw.go4amatch.logic;

import com.witiw.go4amatch.IMainPresenter;
import com.witiw.go4amatch.entities.SportingEvent;
import com.witiw.go4amatch.logic.ahp.AHPAlgorithm;
import com.witiw.go4amatch.logic.objects.Criterion;
import com.witiw.go4amatch.logic.promethee.PrometheeAlgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patryk on 25.05.2017.
 */

public class AlgorithmEnginee extends Thread{

    AHPAlgorithm ahpAlgorithm;
    PrometheeAlgorithm prometheeAlgorithm;
    IMainPresenter mainPresenter;

    public AlgorithmEnginee(IMainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
        ahpAlgorithm = new AHPAlgorithm();
       // prometheeAlgorithm = new PrometheeAlgorithm();
    }

    public void run(List<Criterion> criterias) throws InterruptedException {
        Thread.sleep(100);
        mainPresenter.showResults(new ArrayList<SportingEvent>());
    }

    @Override
    public void run() {

    }
}
