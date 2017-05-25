package com.witiw.go4amatch;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patryk on 24.05.2017.
 */

public class PreferenceFragment extends Fragment {

    SeekBar budget;
    SeekBar attractiveness;
    SeekBar areaAttractiveness;
    SeekBar ligueType;
    SeekBar communication;
    Button searchButton;
    IMainPresenter presenter;
    ProgressDialog ps;

    PreferenceFragment(IMainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preferences, container, false);
        searchButton = (Button) view.findViewById(R.id.btSearch);
        budget = (SeekBar) view.findViewById(R.id.sbBudget);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              ps =  ProgressDialog.show(getContext(), "Please wait ...", "Downloading Image ...", true);
                presenter.performSearching();
                ps.hide();
            }
        });

        attractiveness = (SeekBar) view.findViewById(R.id.sbAttractiveness);
        areaAttractiveness = (SeekBar) view.findViewById(R.id.sbAreaAttractiveness);
        ligueType = (SeekBar) view.findViewById(R.id.sbLigueType);
        communication = (SeekBar) view.findViewById(R.id.sbCommunication);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String link = bundle.getString("url");
        }
    }

    public Button getSearchButton() {
        return searchButton;
    }
}
