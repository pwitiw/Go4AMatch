package com.witiw.go4amatch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

/**
 * Created by Patryk on 07.04.2017.
 */
abstract class BaseTestActivity extends AppCompatActivity {

    private boolean mDestroyed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        final int contentView = getContentViewLayoutResId();
        if (contentView > 0) {
            setContentView(contentView);
        }
        onContentViewSet();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    protected abstract int getContentViewLayoutResId();

    protected void onContentViewSet() {}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDestroyed = true;
    }

    @Override
    public boolean isDestroyed() {
        return mDestroyed;
    }
}