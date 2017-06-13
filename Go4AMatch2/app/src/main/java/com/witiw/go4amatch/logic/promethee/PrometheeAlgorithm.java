package com.witiw.go4amatch.logic.promethee;


import com.witiw.go4amatch.entities.Criterion;
import com.witiw.go4amatch.entities.SportingEvent;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Patryk on 20.05.2017.
 */
public class PrometheeAlgorithm {

    public PrometheeAlgorithm() {
    }

    public void calculateRanking(List<Criterion> criterias, List<SportingEvent> events) {

        double[][] preferenceMatrix = pairwaiseComparationAlternatives(criterias, events);
        calculateFlow(events, preferenceMatrix);
        Collections.sort(events, SportingEvent.Comparator);
    }

    private double[][] pairwaiseComparationAlternatives(List<Criterion> criterias, List<SportingEvent> events) {
        double[][] preferenceMatrix = new double[events.size()][events.size()];
        for (int i = 0; i < events.size(); i++) {
            SportingEvent event = events.get(i);
            for (int j = 0; j < events.size(); j++) {
                double preferenceDegree = 0;
                for (Criterion criterion : criterias) {
                    preferenceDegree += criterion.preferenceFunction(event, events.get(j)) * criterion.getFactor();
                }
                preferenceMatrix[i][j] = preferenceDegree;
            }
        }
        return preferenceMatrix;
    }

    private double calculateFinalValue(List<Criterion> criterias, double[] computedValues) {
        double sum = 0;
        for (int i = 0; i < criterias.size(); i++) {
            sum += computedValues[i] * criterias.get(i).getFactor();
        }
        return sum;
    }

    private double calculatePositiveFlow(int index, double[][] preferenceMatrix) {
        double sum = 0;
        for (int i = 0; i < preferenceMatrix[index].length; i++)
            sum += preferenceMatrix[index][i];
        return sum / (preferenceMatrix[index].length - 1);
    }

    private double calculateNegativeFlow(int index, double[][] preferenceMatrix) {
        double sum = 0;
        for (int i = 0; i < preferenceMatrix.length; i++)
            sum += preferenceMatrix[i][index];
        return sum / (preferenceMatrix.length - 1);
    }

    private void calculateFlow(List<SportingEvent> events, double[][] preferenceMatrix) {
        for (int i = 0; i < events.size(); i++) {
            double positiveFlow = calculatePositiveFlow(i, preferenceMatrix);
            double negativeFlow = calculateNegativeFlow(i, preferenceMatrix);
            events.get(i).setResult(positiveFlow - negativeFlow);
        }
    }

    private void sortResults(List<SportingEvent> events) {
        Collections.sort(events, new Comparator<SportingEvent>() {
            @Override
            public int compare(SportingEvent o1, SportingEvent o2) {
                return (int) Math.round((o1.getResult() - o2.getResult()) * 100) / 100;
            }
        });
    }

}
