package com.witiw.go4amatch;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.witiw.go4amatch.rest.api.SportRadarAPI;
import com.witiw.go4amatch.rest.api.RestServiceFactory;
import com.witiw.go4amatch.rest.sportradar.sdk.RootObject;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    private MainPresenter presenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter = new MainPresenter(this);
    }


    public void testRest() {

        SportRadarAPI http = RestServiceFactory.getSportRadarAPI(SportRadarAPI.class);
        http.getDailySchedule("2017-05-03").enqueue(new Callback<RootObject>() {
            @Override
            public void onResponse(Response<RootObject> response, Retrofit retrofit) {
                if (response.code() == 200) {
                    System.out.print("bad request");
                } else {
                    RootObject r = response.body();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    @Override
    public void showProgress() {
        progressDialog = ProgressDialog.show(this, "Please wait ...", "Downloading Image ...", true);
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showToast(String text) {

    }

    @Override
    public Context getContext() {
        return this;
    }
}