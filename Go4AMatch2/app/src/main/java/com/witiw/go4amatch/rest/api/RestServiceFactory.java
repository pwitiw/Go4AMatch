package com.witiw.go4amatch.rest.api;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.witiw.go4amatch.utils.AppProperties;

import retrofit.Retrofit;
import retrofit.SimpleXmlConverterFactory;

/**
 * Created by Patryk on 05.05.2017.
 */

public class RestServiceFactory {

    public static <S> S getSportRadarAPI(Class<S> serviceClass) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppProperties.BASE_SPORTRADAR_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }

    public static <S> S getGoogleAPI(Class<S> serviceClass) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
//        return new Retrofit.Builder()
//                .baseUrl(BuildConfig.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient)
//                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppProperties.BASE_GOOGLE_URL)
                .client(new OkHttpClient())
//                .connectTimeout(60, TimeUnit.SECONDS)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }
}
