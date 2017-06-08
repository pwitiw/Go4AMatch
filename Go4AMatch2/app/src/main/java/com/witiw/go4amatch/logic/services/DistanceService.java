package com.witiw.go4amatch.logic.services;

import com.witiw.go4amatch.rest.api.sportradar.gameschedule.SportEvent;
import com.witiw.go4amatch.rest.impl.GoogleRestService;

import java.io.IOException;

/**
 * Created by Patryk on 05.06.2017.
 */
public class DistanceService {

    public double getDistance(String currentlocation, SportEvent event) throws IOException {
        if (currentlocation.equals(""))
            throw new IllegalArgumentException();
        String destination = event.getHomeTeam().getName().replace(" ", "+");
        String start = currentlocation.replace(" ", "+");
        return GoogleRestService.getDistanceBetweenCities(destination, start);
    }

}



