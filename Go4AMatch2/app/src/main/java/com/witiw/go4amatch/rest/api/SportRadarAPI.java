package com.witiw.go4amatch.rest.api;

import com.witiw.go4amatch.utils.AppProperties;
import com.witiw.go4amatch.rest.sportradar.sdk.RootObject;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;


/**
 * Created by Patryk on 05.05.2017.
 */

public interface SportRadarAPI {

    @GET(AppProperties.BASE_SPORTRADAR_URL+"schedules/{date}/schedule." + AppProperties.JSON_DOC_TYPE + "?api_key=" + AppProperties.API_KEY)
    Call<RootObject> getDailySchedule(@Path("date") String date);
}
