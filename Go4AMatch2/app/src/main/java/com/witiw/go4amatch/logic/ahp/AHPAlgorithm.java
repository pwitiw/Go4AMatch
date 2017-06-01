package com.witiw.go4amatch.logic.ahp;


import com.witiw.go4amatch.entities.Criterion;

import java.util.List;

/**
 * Created by Patryk on 29.04.2017.
 */
public class AHPAlgorithm {

    double[][] criteriaMatrix;
    final double RI = 0.58;

    public AHPAlgorithm() {
    }

    public void compute(List<Criterion> criteria) {
        criteriaMatrix = setDownCriteriaMatrix(criteria);
        double[] sums = getColumnSums(criteriaMatrix);
        criteriaMatrix = matrixNormalization(criteriaMatrix, sums);
        double[] weights = computeWeights(criteriaMatrix);
        if (isConsistent(weights, sums))
            assignCriteria(criteria, weights);
    }

    private void assignCriteria(List<Criterion> criteria, double[] weights) {
        for (int i = 0; i < criteria.size(); i++) {
            criteria.get(i).setFactor(weights[i]);
        }
    }

    private double[][] setDownCriteriaMatrix(List<Criterion> criteria) {
        double[][] matrix = getInitializedMatrix(criteria.size());
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[i].length; j++) {
                if (i == j) {
                    matrix[i][j] = 1.0;
                } else {
                    //todo sprawdzic czy zaokragla, bo moze lepiej i dokladniej jest zrobic ze calkowita zawsze przypisuje,
                    //i wylicza tylko odwrotnosc
                    matrix[i][j] = SaatyScale.getRange(criteria.get(i), criteria.get(j));

                    matrix[j][i] = 1 / matrix[i][j];
                }
            }
        }
        return matrix;
    }

    private double[][] getInitializedMatrix(int size) {
        double[][] matrix = new double[size][];
        for (int i = 0; i < size; i++) {
            matrix[i] = new double[size];
        }
        return matrix;
    }

    private double[][] matrixNormalization(double[][] matrix, double[] sums) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = matrix[i][j] / sums[j];
            }
        }
        return matrix;
    }

    private double[] getColumnSums(double[][] matrix) {
        double[] sums = new double[matrix[0].length];
        for (int i = 0; i < sums.length; i++) {
            sums[i] = getColumnSum(matrix, i);
        }
        return sums;
    }

    private double getColumnSum(double[][] matrix, int colNr) {
        double sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            sum += matrix[i][colNr];
        }
        return sum;
    }

    private double[] computeWeights(double[][] matrix) {
        double[] weights = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                weights[i] += matrix[i][j];
            }
            weights[i] /= matrix[i].length;
        }
        return weights;
    }

    private boolean isConsistent(double[] weights, double[] sums) {
        double lambda = 0;
        for (int i = 0; i < weights.length; i++)
            lambda += sums[i] * weights[i];

        double CI = (lambda - weights.length) / (weights.length - 1);
        double CR = CI / RI;
        return CR < 0.1;
    }
}
