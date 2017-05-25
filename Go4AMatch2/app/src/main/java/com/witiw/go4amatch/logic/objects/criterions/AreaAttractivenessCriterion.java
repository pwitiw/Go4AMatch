package com.witiw.go4amatch.logic.objects.criterions;


import com.witiw.go4amatch.entities.SportingEvent;
import com.witiw.go4amatch.logic.ahp.Importance;
import com.witiw.go4amatch.logic.objects.Criterion;

/**
 * Created by Patryk on 24.05.2017.
 */
public class AreaAttractivenessCriterion extends Criterion {

    public AreaAttractivenessCriterion(String name, Importance importance) {
        super(name, importance);
    }

    public AreaAttractivenessCriterion(double factor) {
        super(factor);
    }

    @Override
    public double preferenceFunction(SportingEvent event1, SportingEvent event2) {
        return function(event1.getAreaAttractiveness(), event2.getAreaAttractiveness());
    }
}