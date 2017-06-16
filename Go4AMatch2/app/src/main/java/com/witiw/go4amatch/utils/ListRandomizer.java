package com.witiw.go4amatch.utils;

import android.util.Log;

import com.witiw.go4amatch.IMainPresenter;
import com.witiw.go4amatch.entities.SportingEvent;
import com.witiw.go4amatch.logic.DataProcessingService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patryk on 13.06.2017.
 */

public class ListRandomizer {

    public static void generateRandomLists(IMainPresenter mainPresenter) {
        List<SportingEvent> list1 = new ArrayList<>();
        List<SportingEvent> list2 = new ArrayList<>();
        List<SportingEvent> noFootballEvents = new ArrayList<>();
        DataProcessingService dataProcessingService = new DataProcessingService(mainPresenter);
        List<SportingEvent> sportingEvents = dataProcessingService.processDataFromXmlFile(AppProperties.TEST_DATA_FILE_NAME);
        noFootballEvents.addAll(sportingEvents.subList(sportingEvents.size() - 6, sportingEvents.size()));
        sportingEvents.removeAll(noFootballEvents);
        while (list1.size() < 17 && list2.size() < 17) {
            list1.add(getRandomEvent(sportingEvents));
            list2.add(getRandomEvent(sportingEvents));
        }
        while (list1.size() < 20 && list2.size() < 20) {
            list1.add(getRandomEvent(noFootballEvents));
            list2.add(getRandomEvent(noFootballEvents));
        }
        logResults("List 1", list1);
        logResults("List 2", list2);
    }

    private static SportingEvent getRandomEvent(List<SportingEvent> events) {
        int number = (int) Math.random() * (events.size() - 1);
        SportingEvent sportingEvent = events.get(number);
        events.remove(number);
        return sportingEvent;
    }

    private static void logResults(String text, List<SportingEvent> events) {
        StringBuilder stringBuilder = new StringBuilder();
        for (SportingEvent event : events) {
            stringBuilder.append(event.toString());
        }
        Log.d(text, stringBuilder.toString());
    }
}
