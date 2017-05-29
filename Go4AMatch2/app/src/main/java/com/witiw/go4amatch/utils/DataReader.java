package com.witiw.go4amatch.utils;

import android.content.Context;

import com.witiw.go4amatch.entities.SportingEvent;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Patryk on 26.05.2017.
 */

public class DataReader {

    private Context context;
    private static final String FILE_NAME="sporting_events.xml";

    public DataReader(Context context) {
        this.context = context;
    }

    public void readData(String fileName){
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(context.getAssets().open(FILE_NAME));
            doc.getDocumentElement().normalize();
            List<SportingEvent> events = XmlPojoConverter.convert(doc);
            doc.getDoctype();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
//    List<HashMap<String,String>> weatherDataCollection =
//            new ArrayList<HashMap<String,String>>();

}
