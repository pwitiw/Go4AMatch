package com.witiw.go4amatch.rest.api;

import com.witiw.go4amatch.rest.google.PlaceSearchResponse;
import com.witiw.go4amatch.utils.AppProperties;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by Patryk on 26.05.2017.
 */

public interface GoogleAPI {

    //    @GET(AppProperties.BASE_GOOGLE_URL + "place/textsearch/" + AppProperties.JSON_DOC_TYPE + "?query=attractions+in+city&key=" + AppProperties.GOOGLE_PLACES_API_KEY)
//    Call<JSONObject> getAttractionsForCity(@Query("city") String city);
    @GET(AppProperties.BASE_GOOGLE_URL + "place/textsearch/xml?query=attractions+in+Wroclaw&key=" + AppProperties.GOOGLE_PLACES_API_KEY)
    Call<PlaceSearchResponse> getAttractionsForCity();
}
