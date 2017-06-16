package com.witiw.go4amatch.utils;

import android.util.Log;

import com.witiw.go4amatch.IMainPresenter;
import com.witiw.go4amatch.entities.SportingEvent;
import com.witiw.go4amatch.logic.DataProcessingService;
import com.witiw.go4amatch.logic.promethee.PrometheeAlgorithm;

import java.util.List;

/**
 * Created by Patryk on 13.06.2017.
 */

public class ListPrinter {

    public static void printShortList(IMainPresenter mainPresenter, String filename) {

        DataProcessingService dataProcessingService = new DataProcessingService(mainPresenter);
        List<SportingEvent> sportingEvents = dataProcessingService.processDataFromXmlFile(filename);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = sportingEvents.size() - 1, j = 1; i >= 0; i--, j++) {
            stringBuilder.append(j + "." + sportingEvents.get(i).toFormShortString());
        }
        Log.i(filename, stringBuilder.toString());
    }

    public static void printLongList(List<SportingEvent> sportingEvents) {

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = sportingEvents.size() - 1, j = 1; i >= 0; i--, j++) {
            stringBuilder.append(j + "." + sportingEvents.get(i).toFormLongString());
        }
        Log.i("LISTA: ", stringBuilder.toString());
    }

    public static void printResults(List<SportingEvent> events, List<SportingEvent> ranking) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < ranking.size(); i++) {
            for (int j = events.size() - 1, k = 1; i >= 0; j--, k++) {
                if (events.get(j).equals(ranking.get(i))) {
//                    stringBuilder.append(k + "." + ranking.get(i).toVersusString());
                    stringBuilder.append("\n" + k);
                    break;
                }
            }
        }
        Log.i("RANKING: ", stringBuilder.toString());
    }


    public static void printMatches(IMainPresenter mainPresenter, String filename) {

        DataProcessingService dataProcessingService = new DataProcessingService(mainPresenter);
        List<SportingEvent> sportingEvents = dataProcessingService.processDataFromXmlFile(filename);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = sportingEvents.size() - 1, j = 1; i >= 0; i--, j++) {
            stringBuilder.append(j + ". " + sportingEvents.get(i).toVersusString());
        }
        Log.i(filename, stringBuilder.toString());
    }
}
