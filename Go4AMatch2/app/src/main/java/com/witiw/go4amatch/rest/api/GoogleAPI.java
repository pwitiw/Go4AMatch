package com.witiw.go4amatch.rest.api;

import com.witiw.go4amatch.rest.api.google.PlaceSearchResponse;
import com.witiw.go4amatch.rest.api.google.distance.DistanceMatrixResponse;
import com.witiw.go4amatch.utils.AppProperties;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Patryk on 26.05.2017.
 */

public interface GoogleAPI {

    @GET(AppProperties.BASE_GOOGLE_URL + "place/textsearch/" + AppProperties.XML_DOC_TYPE + "?")
    Call<PlaceSearchResponse> getAttractionsForCity(@Query("query") String cityQuery, @Query("key") String key);


    //    @GET(AppProperties.BASE_GOOGLE_URL + "distancematrix/" + AppProperties.XML_DOC_TYPE + "?units=metric&origins=startCity&destinations=endCity&key=" + AppProperties.GOOGLE_DISTANCE_API_KEY)
    @GET(AppProperties.BASE_GOOGLE_URL + "distancematrix/" + AppProperties.XML_DOC_TYPE + "?units=metric")
    Call<DistanceMatrixResponse> getDistanceBetweenCities(@Query("origins") String origins, @Query("destinations") String destination, @Query("key") String key);


}