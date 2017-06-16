package com.witiw.go4amatch.entities.criteria;

import com.witiw.go4amatch.entities.SportingEvent;
import com.witiw.go4amatch.entities.Importance;
import com.witiw.go4amatch.entities.Criterion;

/**
 * Created by Patryk on 24.05.2017.
 */
public class BudgetCriterion extends Criterion {

    private double TRESHOLD = 100.0;

    public BudgetCriterion(Importance importance) {
        super("Budżet", importance);
    }

    public BudgetCriterion(double factor) {
        super(factor);
    }

    @Override
    public double preferenceFunction(SportingEvent event1, SportingEvent event2) {
        return function(event1.getBudget(), event2.getBudget());
    }

    //  V preference function
    @Override
    protected double function(double arg1, double arg2) {
        double d = arg2 - arg1;
        if (d <= 0)
            return 0;
        if (d > 0 && d < TRESHOLD)
            return d / TRESHOLD;
        else
            return 1;
    }
}
