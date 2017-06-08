package com.witiw.go4amatch.rest.api;

import com.witiw.go4amatch.rest.api.google.PlaceSearchResponse;
import com.witiw.go4amatch.rest.api.google.distance.DistanceMatrixResponse;
import com.witiw.go4amatch.utils.AppProperties;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Patryk on 26.05.2017.
 */

public interface GoogleAPI {

    @GET(AppProperties.BASE_GOOGLE_URL + "place/textsearch/" + AppProperties.XML_DOC_TYPE + "?query=attractions+in+city&key=" + AppProperties.GOOGLE_PLACES_API_KEY)
    Call<PlaceSearchResponse> getAttractionsForCity(@Query("city") String city);


    @GET(AppProperties.BASE_GOOGLE_URL + "distancematrix/" + AppProperties.XML_DOC_TYPE + "?units=imperial&origins=startCity&destinations=endCity&key=" + AppProperties.GOOGLE_DISTANCE_API_KEY)
    Call<DistanceMatrixResponse> getDistanceBetweenCities(@Query("startCity") String origins, @Query("endCity") String destination);

}