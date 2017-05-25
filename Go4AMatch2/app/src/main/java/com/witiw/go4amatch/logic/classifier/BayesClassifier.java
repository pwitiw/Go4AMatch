package com.witiw.go4amatch.logic.classifier;


import com.witiw.go4amatch.logic.DataReader;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

/**
 * Created by Patryk on 16.05.2017.
 */
public class BayesClassifier {

    private final NaiveBayes naiveBayes;
    private Instances inscantes;

    public BayesClassifier() throws Exception {
        naiveBayes = new NaiveBayes();
        naiveBayes.setUseSupervisedDiscretization(true);
        inscantes = DataReader.getInstancesForTrainingData();
        validate();
    }

    public NaiveBayes getNaiveBayes() {
        return naiveBayes;
    }

    /* Leave-one-out cross validation*/
    public void validate() throws Exception {
        Instances trainingData;
        Instance testInstance;
        int accuracyCounter = 0;
        for (int i = 0; i < inscantes.numInstances(); i++) {
            trainingData = getInstancesWithout(i);
            testInstance = inscantes.instance(i);
            naiveBayes.buildClassifier(trainingData);
            if (testInstance.classValue() == naiveBayes.classifyInstance(testInstance))
                accuracyCounter++;
            else
                System.out.println(testInstance.toString());
        }
        System.out.println("Accuracy:  " + Math.round(accuracyCounter * 1000 / (double) inscantes.numInstances()) / 10.0);
    }

    private Instances getInstancesWithout(int index) {
        Instances ins = new Instances(inscantes);
        ins.delete(index);
        return ins;
    }

    private void printResult(double[] result) {
        System.out.print("CIEKAWY:" + Math.round(result[0] * 1000) / 10.0 + "%" + "\t\t\t");
        System.out.print("NIECIEKAWY:" + Math.round(result[1] * 1000) / 10.0 + "%" + "\t\t");
        String r = result[0] > result[1] ? "CIEKAWY" : "NIECIEKAWY";
        System.out.println(r);
    }

    private double calculateAccuracy() {
        FastVector s;
        Evaluation evaluation;
        return 2;
    }
}
