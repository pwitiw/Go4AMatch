package com.witiw.go4amatch;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.view.View.TEXT_ALIGNMENT_CENTER;

/**
 * Created by Patryk on 07.05.2017.
 */

public class TabBuilder {

    private final int size = 35;
    private static int TAB_COUNTER = 0;
    private TextView textView;
    private Activity activity;

    public TabBuilder(final Activity activity) {
        this.activity = activity;
        textView = (TextView) LayoutInflater.from(activity).inflate(R.layout.custom_tab, null);
    }

    public TabBuilder withText(final int stringValue) {
        textView.setText(stringValue);
        textView.setTextSize(size);
        textView.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        return this;
    }

    public TabBuilder withDrawable(final int drawable) {
        Drawable icon = ContextCompat.getDrawable(activity, drawable);
        icon.setBounds(0, 0, size, size);
        textView.setCompoundDrawablePadding(10);
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, icon, null);
        return this;
    }

    public void buildForLayout(TabLayout tabLayout) {
        if (tabLayout.getTabAt(TAB_COUNTER) != null)
            tabLayout.getTabAt(TAB_COUNTER++).setCustomView(textView);
        else
            Log.i("WARNING", "Too many tabs wanted to be created");
    }
}
