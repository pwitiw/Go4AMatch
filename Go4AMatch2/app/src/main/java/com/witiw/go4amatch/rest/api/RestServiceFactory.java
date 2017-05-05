package com.witiw.go4amatch.rest.api;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.witiw.go4amatch.AppProperties;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Patryk on 05.05.2017.
 */

public class RestServiceFactory {

    public static <S> S createService(Class<S> serviceClass) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppProperties.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(serviceClass);
    }

}
