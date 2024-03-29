package com.witiw.go4amatch;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.witiw.go4amatch.entities.Criterion;
import com.witiw.go4amatch.entities.SportingEvent;
import com.witiw.go4amatch.logic.AlgorithmEnginee;

import java.util.List;

/**
 * Created by Patryk on 25.05.2017.
 */
public class MainPresenter implements IMainPresenter {

    PreferenceFragment preferenceFragment;
    ResultFragment resultFragment;
    IMainActivity mainActivity;
    TabLayout tabLayout;
    ViewPagerAdapter adapter;


    public MainPresenter(MainActivity activity) {
        mainActivity = activity;
        setupViewPager(activity);
    }

    private void setupViewPager(FragmentActivity activity) {
        ViewPager viewPager = (ViewPager) activity.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) activity.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        adapter = new ViewPagerAdapter(activity.getSupportFragmentManager());
        preferenceFragment = new PreferenceFragment(this);
        resultFragment = new ResultFragment(this);
        adapter.addFragment(preferenceFragment, "Preferences");
        adapter.addFragment(resultFragment, "List");
        viewPager.setAdapter(adapter);
        setupTabIcons(activity, tabLayout);
    }

    private void setupTabIcons(Activity activity, TabLayout tabLayout) {
        new TabBuilder(activity)
                .withText(R.string.preferences_mode)
                .withDrawable(R.drawable.ic_search)
                .buildForLayout(tabLayout);
        new TabBuilder(activity)
                .withText(R.string.list_mode)
                .withDrawable(R.drawable.ic_list)
                .buildForLayout(tabLayout);
    }

    @Override
    public void performSearching(List<Criterion> criterias) {
        new AlgorithmEnginee(this).execute(criterias.toArray(new Criterion[criterias.size()]));
    }

    @Override
    public void showResults(List<SportingEvent> events) {
        resultFragment.updateData(events);
        tabLayout.getTabAt(1).select();
    }

    @Override
    public Context getContext() {
        return mainActivity.getContext();
    }

    @Override
    public void showProgress() {
        preferenceFragment.initializeProgressDialog(getContext());
    }

    @Override
    public void hideProgress() {
        preferenceFragment.cancelProgressDialog();
    }

    @Override
    public String getLocation() {
        return preferenceFragment.getLocationText();
    }

    @Override
    public void showToast(String text) {
        Toast toast = Toast.makeText(getContext(), text, Toast.LENGTH_LONG);
        toast.setGravity(toast.getGravity(),toast.getXOffset(),100);
        toast.show();
    }

}
