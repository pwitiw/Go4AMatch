package com.witiw.go4amatch.entities.criterions;

import com.witiw.go4amatch.entities.SportingEvent;
import com.witiw.go4amatch.logic.ahp.Importance;
import com.witiw.go4amatch.entities.Criterion;
/**
 * Created by Patryk on 24.05.2017.
 */
public class AttractivenessCriterion extends Criterion {

    public AttractivenessCriterion(Importance importance) {
        super("Atrakcyjność", importance);
    }

    public AttractivenessCriterion(double factor) {
        super(factor);
    }

    @Override
    public double preferenceFunction(SportingEvent event1, SportingEvent event2) {
        return function(event1.getAttractiveness().getValue(),event2.getAttractiveness().getValue());
    }
}
