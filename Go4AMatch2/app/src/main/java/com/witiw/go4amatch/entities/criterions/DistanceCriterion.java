package com.witiw.go4amatch.entities.criterions;

import com.witiw.go4amatch.entities.SportingEvent;
import com.witiw.go4amatch.logic.ahp.Importance;
import com.witiw.go4amatch.entities.Criterion;

/**
 * Created by Patryk on 24.05.2017.
 */
public class DistanceCriterion extends Criterion {

    public DistanceCriterion(Importance importance) {
        super("Odległość", importance);
    }

    public DistanceCriterion(double factor) {
        super(factor);
    }

    @Override
    public double preferenceFunction(SportingEvent event1, SportingEvent event2) {
        return function(event1.getDistance(), event2.getDistance());
    }

    @Override
    protected double function(double arg1, double arg2) {
        return arg1 - arg2 >= 0 ? 0 : 1;
    }
}
