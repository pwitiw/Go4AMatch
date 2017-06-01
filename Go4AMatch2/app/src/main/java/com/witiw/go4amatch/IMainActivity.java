package com.witiw.go4amatch;

import android.content.Context;

/**
 * Created by Patryk on 25.05.2017.
 */

public interface IMainActivity {

    void showProgress(Context context);

    void hideProgress();

    void showToast(String text);

    Context getContext();
}

