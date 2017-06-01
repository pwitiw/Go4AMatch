package com.witiw.go4amatch.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;

import com.witiw.go4amatch.entities.SportingEvent;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import weka.core.Instances;

/**
 * Created by Patryk on 26.05.2017.
 */

public class DataReader {

    private static Context APP_CONTEXT;
    private static final String TEST_DATA_FILE_NAME = "sporting_events.xml";
    private static final String TRAINING_DATA_FILE_NAME = "training_data_bayes.arff";

    public static Document readTestData() {
        Document doc = null;
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
             doc = docBuilder.parse(APP_CONTEXT.getAssets().open(TEST_DATA_FILE_NAME));
            doc.getDocumentElement().normalize();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public static Instances readTrainingData() {
        Instances data = null;
        try {
            InputStream inputStream = APP_CONTEXT.getAssets().open(TRAINING_DATA_FILE_NAME);
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            data = new Instances(reader);
            data.setClassIndex(data.numAttributes() - 1);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void setAppContext(Context appContext) {
        APP_CONTEXT = appContext;
    }
}
