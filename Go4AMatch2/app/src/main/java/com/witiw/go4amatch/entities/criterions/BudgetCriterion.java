package com.witiw.go4amatch.entities.criterions;

import com.witiw.go4amatch.entities.SportingEvent;
import com.witiw.go4amatch.entities.Importance;
import com.witiw.go4amatch.entities.Criterion;

/**
 * Created by Patryk on 24.05.2017.
 */
public class BudgetCriterion extends Criterion {

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

    @Override
    protected double function(double arg1, double arg2) {

        return arg1 - arg2 >= 0 ? 0 : 1;
    }
}
