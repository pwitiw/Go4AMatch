package com.witiw.go4amatch.entities;


import com.witiw.go4amatch.logic.ahp.Importance;

/**
 * Created by Patryk on 21.05.2017.
 */
public abstract class Criterion {
    protected Double factor;
    String name;
    private Importance importance;

    public Criterion(String name, Importance importance) {
        this.name = name;
        this.importance = importance;
        factor = 0.0;
    }

    public Criterion(double factor) {
        this.factor = factor;
    }

    public Double getFactor() {
        return factor;
    }

    public void setFactor(Double factor) {
        this.factor = factor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Importance getImportance() {
        return importance;
    }

    public void setImportance(Importance importance) {
        this.importance = importance;
    }

    public abstract double preferenceFunction(SportingEvent event1, SportingEvent event2);

    //fixme dostosowac funkcje do kazdego kryterium
    protected double function(double arg1, double arg2) {
        return arg1 > arg2 ? 1 : 0;
    }
}
