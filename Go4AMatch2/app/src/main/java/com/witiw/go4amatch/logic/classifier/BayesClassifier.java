package com.witiw.go4amatch.logic.classifier;


import com.witiw.go4amatch.entities.Attractiveness;
import com.witiw.go4amatch.utils.DataReader;

import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instance;
import weka.core.Instances;

/**
 * Created by Patryk on 16.05.2017.
 */
public class BayesClassifier {
    private final NaiveBayes naiveBayes;
    private Instances instances;

    public BayesClassifier() throws Exception {
        naiveBayes = new NaiveBayes();
        naiveBayes.setUseSupervisedDiscretization(true);
        instances = DataReader.readTrainingData();
        if (instances == null)
            throw new RuntimeException();
        naiveBayes.buildClassifier(instances);
    }

    public double classify(Attractiveness attractiveness) throws Exception {
        Instance instance = getInstanceForAttractiveness(attractiveness);
        return naiveBayes.distributionForInstance(instance)[1];
    }

    /* Leave-one-out cross validation*/
    public void validate() throws Exception {
        Instances trainingData;
        Instance testInstance;
        int accuracyCounter = 0;
        for (int i = 0; i < instances.numInstances(); i++) {
            trainingData = getInstancesWithout(i);
            testInstance = instances.instance(i);
            naiveBayes.buildClassifier(trainingData);
            if (testInstance.classValue() == naiveBayes.classifyInstance(testInstance))
                accuracyCounter++;
            else
                System.out.println(testInstance.toString());
        }
        System.out.println("Accuracy:  " + Math.round(accuracyCounter * 1000 / (double) instances.numInstances()) / 10.0);
    }

    private Instances getInstancesWithout(int index) {
        Instances ins = new Instances(instances);
        ins.delete(index);
        return ins;
    }

    private void printResult(double[] result) {
        System.out.print("CIEKAWY:" + Math.round(result[1] * 1000) / 10.0 + "%" + "\t\t\t");
        System.out.print("NIECIEKAWY:" + Math.round(result[0] * 1000) / 10.0 + "%" + "\t\t");
        String r = result[1] > result[0] ? "CIEKAWY" : "NIECIEKAWY";
        System.out.println(r);
    }

    private Instance getInstanceForAttractiveness(Attractiveness attractiveness) {
        Instance instance = new Instance(instances.numAttributes());
        instance.setDataset(instances);
        instances.add(instance);
        instance.setValue(0,attractiveness.getCondition().getName());
        instance.setValue(1,attractiveness.getImportance());
        instance.setValue(2,attractiveness.getPosition());
        instance.setValue(3,attractiveness.getIsDerby());

        return instance;
    }
}
