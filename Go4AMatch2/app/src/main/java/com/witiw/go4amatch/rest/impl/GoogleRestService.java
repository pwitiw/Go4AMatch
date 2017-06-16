package com.witiw.go4amatch.rest.impl;

import com.witiw.go4amatch.rest.api.GoogleAPI;
import com.witiw.go4amatch.rest.api.RestServiceFactory;
import com.witiw.go4amatch.rest.api.google.PlaceSearchResponse;
import com.witiw.go4amatch.rest.api.google.distance.DistanceMatrixResponse;
import com.witiw.go4amatch.utils.AppProperties;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;

import retrofit.Response;

/**
 * Created by Patryk on 02.06.2017.
 */

public class GoogleRestService {

    private static GoogleAPI googleAPI = RestServiceFactory.getSportRadarAPI(GoogleAPI.class);

    public static int GOOGLE_REST_SERVICE_COUNTER = 0;

    public static int getAttractionsForCity(String city) throws IOException, InterruptedException {
        GOOGLE_REST_SERVICE_COUNTER++;
        int amount = 0;
        boolean breakFlag = true;

        String properUrl = URLEncoder.encode("attractions in " + city, "UTF-8");
        Response<PlaceSearchResponse> placeSearchResponse = googleAPI.getAttractionsForCity("attractions in " + city, AppProperties.GOOGLE_PLACES_API_KEY_2).execute();

        while (breakFlag) {
            amount += getSize(placeSearchResponse);
            breakFlag = false;
            if (placeSearchResponse.body().getNextPageToken() != null) {
                breakFlag = true;
                Thread.sleep(2000);
                placeSearchResponse = googleAPI.getNextPageAttractionfsForCity(properUrl, AppProperties.GOOGLE_PLACES_API_KEY_2, placeSearchResponse.body().getNextPageToken()).execute();
            }
        }
        return amount;
    }

    public static int getDistanceBetweenCities(String origins, String destination) throws IOException {
        GOOGLE_REST_SERVICE_COUNTER++;
        Response<DistanceMatrixResponse> response = googleAPI.getDistanceBetweenCities(origins, destination, AppProperties.GOOGLE_DISTANCE_API_KEY).execute();
        return response.body().getDistance();
    }

    private static String convertCityName(String place) {
        return place.replace(" ", "+");
    }

    private static int getSize(Response<PlaceSearchResponse> placeSearchResponse) {
        if (placeSearchResponse.body().getResults() == null)
            return 0;
        return placeSearchResponse.body().getResults().size();
    }
}