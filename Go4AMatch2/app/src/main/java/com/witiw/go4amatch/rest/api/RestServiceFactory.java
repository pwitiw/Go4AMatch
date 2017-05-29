package com.witiw.go4amatch.rest.api;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.witiw.go4amatch.utils.AppProperties;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by Patryk on 05.05.2017.
 */

public class RestServiceFactory {

    public static <S> S getSportRadarAPI(Class<S> serviceClass) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppProperties.BASE_SPORTRADAR_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))

                .build();
        return retrofit.create(serviceClass);
    }

    public static <S> S getGoogleAPI(Class<S> serviceClass) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppProperties.BASE_GOOGLE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }

}
