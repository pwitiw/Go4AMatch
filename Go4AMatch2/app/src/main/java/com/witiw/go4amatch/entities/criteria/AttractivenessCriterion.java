package com.witiw.go4amatch.entities.criteria;

import android.util.Log;

import com.witiw.go4amatch.entities.SportingEvent;
import com.witiw.go4amatch.entities.Importance;
import com.witiw.go4amatch.entities.Criterion;

/**
 * Created by Patryk on 24.05.2017.
 */
public class AttractivenessCriterion extends Criterion {

    private final static double TRESHOLD = 0.05;

    public AttractivenessCriterion(Importance importance) {
        super("Atrakcyjność", importance);
    }

    public AttractivenessCriterion(double factor) {
        super(factor);
    }

    @Override
    public double preferenceFunction(SportingEvent event1, SportingEvent event2) {
        return function(event1.getAttractiveness().getValue(), event2.getAttractiveness().getValue());
    }

//    @Override
//    protected double function(double arg1, double arg2) {
//        double result = arg1 - arg2;
//
//        if (result > TRESHOLD)
//            return 1;
//        else if (result < -TRESHOLD)
//            return 0;
//        else
//            return 0.5;
//    }
}
