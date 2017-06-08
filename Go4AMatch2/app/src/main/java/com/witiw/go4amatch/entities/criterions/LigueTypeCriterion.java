package com.witiw.go4amatch.entities.criterions;

import com.witiw.go4amatch.entities.SportingEvent;
import com.witiw.go4amatch.entities.Importance;
import com.witiw.go4amatch.entities.Criterion;

/**
 * Created by Patryk on 24.05.2017.
 */
public class LigueTypeCriterion extends Criterion {

    public LigueTypeCriterion(Importance importance) {
        super("Rodzaj rozgrywek", importance);
    }

    public LigueTypeCriterion(double factor) {
        super(factor);
    }

    @Override
    public double preferenceFunction(SportingEvent event1, SportingEvent event2) {
        return function(event1.getLeagueType().getUefaCoefficient(),event2.getLeagueType().getUefaCoefficient());
    }
}
