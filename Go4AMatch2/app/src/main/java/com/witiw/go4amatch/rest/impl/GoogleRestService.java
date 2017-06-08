package com.witiw.go4amatch.rest.impl;

import com.witiw.go4amatch.rest.api.GoogleAPI;
import com.witiw.go4amatch.rest.api.RestServiceFactory;
import com.witiw.go4amatch.rest.api.google.PlaceSearchResponse;

import java.io.IOException;

/**
 * Created by Patryk on 02.06.2017.
 */

public class GoogleRestService {

    private static GoogleAPI googleAPI = RestServiceFactory.getSportRadarAPI(GoogleAPI.class);

    public static int GOOGLE_REST_SERVICE_COUNTER =0;

    public static PlaceSearchResponse getAttractionsForCity(String city) throws IOException {
        GOOGLE_REST_SERVICE_COUNTER++;
        return googleAPI.getAttractionsForCity(city).execute().body();

    }

    public static int getDistanceBetweenCities(String origins, String destination) throws IOException {
        GOOGLE_REST_SERVICE_COUNTER++;
        return googleAPI.getDistanceBetweenCities(origins, destination).execute().body().getDistance();
    }
}