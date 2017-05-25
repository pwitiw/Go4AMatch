package com.witiw.go4amatch.logic.promethee;


import com.witiw.go4amatch.entities.SportingEvent;
import com.witiw.go4amatch.logic.objects.Criterion;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Patryk on 20.05.2017.
 */
public class PrometheeAlgorithm {

    List<Criterion> criterias;

    public PrometheeAlgorithm(List<? extends Criterion> criterions) {
        //fixme  this.criterias = PrometheeCriterion.asPrometheeCriterionList(criterions);
    }

    public List<SportingEvent> calculateRanking(List<SportingEvent> events) {

        pairwaiseComparationAlternatives(events);
        //calculate flow
        return new LinkedList<SportingEvent>();
    }

    //todo body
    private double[][] pairwaiseComparationAlternatives(List<SportingEvent> events) {
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

    private double calculateFinalValue(double[] computedValues) {
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

    private double calculateFlow(int index, double[][] preferenceMatrix) {
        double positiveFlow = calculatePositiveFlow(index, preferenceMatrix);
        double negativeFlow = calculateNegativeFlow(index, preferenceMatrix);

        return positiveFlow - negativeFlow;
    }

    public List<Criterion> getCriterias() {
        return criterias;
    }

}
