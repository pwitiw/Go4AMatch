package com.witiw.go4amatch;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.witiw.go4amatch.entities.SportingEvent;
import com.witiw.go4amatch.logic.AlgorithmEnginee;
import com.witiw.go4amatch.logic.objects.Criterion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patryk on 25.05.2017.
 */
public class MainPresenter implements IMainPresenter {

    PreferenceFragment preferenceFragment;
    ResultFragment resultFragment;
    IMainActivity mainActivity;
    AlgorithmEnginee algorithmEnginee;

    public MainPresenter(MainActivity activity) {
        algorithmEnginee = new AlgorithmEnginee(this, activity);
        mainActivity = activity;
        setupViewPager(activity);
    }

    private void setupViewPager(FragmentActivity activity) {
        ViewPager viewPager = (ViewPager) activity.findViewById(R.id.viewpager);
        TabLayout tabLayout = (TabLayout) activity.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(activity.getSupportFragmentManager());
        preferenceFragment = new PreferenceFragment(this);
        resultFragment = new ResultFragment(this);
        adapter.addFragment(preferenceFragment, "Preferences");
        adapter.addFragment(resultFragment, "List");
        adapter.addFragment(new ResultFragment(this), "Map");
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
        new TabBuilder(activity)
                .withText(R.string.map_mode)
                .withDrawable(R.drawable.ic_map)
                .buildForLayout(tabLayout);
    }

    @Override
    public void performSearching() {
        mainActivity.showProgress();
        try {
            algorithmEnginee.run(new ArrayList<Criterion>());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showResults(List<SportingEvent> events) {
        mainActivity.hideProgress();
    }
}
