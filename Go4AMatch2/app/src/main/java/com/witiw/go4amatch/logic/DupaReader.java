package com.witiw.go4amatch.logic;

import com.witiw.go4amatch.utils.AppProperties;

import weka.core.Instances;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Patryk on 16.05.2017.
 */
public class DupaReader {

    public static Instances getInstancesForTrainingData() {
        Instances data = null;
        try {
            BufferedReader reader =
                    new BufferedReader(new FileReader(AppProperties.TRAINING_DATA_NAIVE_BAYES_PATH));
            data = new Instances(reader);
            data.setClassIndex(data.numAttributes() - 1);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
