package com.witiw.go4amatch.rest.api;

import com.witiw.go4amatch.AppProperties;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by Patryk on 05.05.2017.
 */

public interface HttpHelper {

    @GET("schedules/{date}/schedule." + AppProperties.DOC_TYPE + "?api_key=" + AppProperties.API_KEY)
    Call<ResponseBody> getDailySchedule(@Path("date") String date);
}
