package com.witiw.go4amatch.logic.classifier;


import com.witiw.go4amatch.entities.SportingEvent;

/**
 * Created by Patryk on 21.05.2017.
 */
public class PrometheeEntity extends SportingEvent {

    private double positivePreferenceFlow;
    private double negativePreferenceFlow;

    public PrometheeEntity(SportingEvent sportingEvent) {
        super(sportingEvent);
    }

    public double getPositivePreferenceFlow() {
        return positivePreferenceFlow;
    }

    public void setPositivePreferenceFlow(double positivePreferenceFlow) {
        this.positivePreferenceFlow = positivePreferenceFlow;
    }

    public double getNegativePreferenceFlow() {
        return negativePreferenceFlow;
    }

    public void setNegativePreferenceFlow(double negativePreferenceFlow) {
        this.negativePreferenceFlow = negativePreferenceFlow;
    }

    public void computeFlows(double[] compared) {


    }
}
