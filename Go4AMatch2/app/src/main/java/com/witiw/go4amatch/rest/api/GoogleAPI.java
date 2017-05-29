package com.witiw.go4amatch.rest.api;

import com.squareup.okhttp.ResponseBody;
import com.witiw.go4amatch.utils.AppProperties;

import org.json.JSONObject;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Patryk on 26.05.2017.
 */

public interface GoogleAPI {

    //    @GET(AppProperties.BASE_GOOGLE_URL + "place/textsearch/" + AppProperties.JSON_DOC_TYPE + "?query=attractions+in+city&key=" + AppProperties.GOOGLE_PLACES_API_KEY)
//    Call<JSONObject> getAttractionsForCity(@Query("city") String city);
    @GET(AppProperties.BASE_GOOGLE_URL + "place/textsearch/json?query=attractions+in+new+york&key=" + AppProperties.GOOGLE_PLACES_API_KEY)
    Call<ResponseBody> getAttractionsForCity();
}
