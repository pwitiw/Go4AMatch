package com.witiw.go4amatch;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.witiw.go4amatch.entities.Criterion;
import com.witiw.go4amatch.entities.Importance;
import com.witiw.go4amatch.entities.criteria.AreaAttractivenessCriterion;
import com.witiw.go4amatch.entities.criteria.AttractivenessCriterion;
import com.witiw.go4amatch.entities.criteria.BudgetCriterion;
import com.witiw.go4amatch.entities.criteria.DistanceCriterion;
import com.witiw.go4amatch.entities.criteria.LigueTypeCriterion;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patryk on 24.05.2017.
 */

public class PreferenceFragment extends Fragment {

    private DiscreteSeekBar budget;
    private DiscreteSeekBar attractiveness;
    private DiscreteSeekBar areaAttractiveness;
    private DiscreteSeekBar ligueType;
    private DiscreteSeekBar distance;
    private Button searchButton;
    private AutoCompleteTextView textView;
    private IMainPresenter presenter;
    private ProgressDialog mProgressDialog;

    PreferenceFragment(IMainPresenter presenter) {
        this.presenter = presenter;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preferences, container, false);
        searchButton = (Button) view.findViewById(R.id.btSearch);
        textView = (AutoCompleteTextView) view.findViewById(R.id.tvLocation);
        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                hideSoftKeyboard();
                List<Criterion> criterias = getCrieria();
                presenter.performSearching(criterias);
            }
        });

        budget = (DiscreteSeekBar) view.findViewById(R.id.sbBudget);
        attractiveness = (DiscreteSeekBar) view.findViewById(R.id.sbAttractiveness);
        areaAttractiveness = (DiscreteSeekBar) view.findViewById(R.id.sbAreaAttractiveness);
        ligueType = (DiscreteSeekBar) view.findViewById(R.id.sbLigueType);
        distance = (DiscreteSeekBar) view.findViewById(R.id.sbDistance);
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

    public List<Criterion> getCrieria() {
        final List<Criterion> criteria = new ArrayList<>();
        if (budget.getProgress() > 0)
            criteria.add(new BudgetCriterion(Importance.valueOf(budget.getProgress())));
        if (distance.getProgress() > 0)
            criteria.add(new DistanceCriterion(Importance.valueOf(distance.getProgress())));
        if (ligueType.getProgress() > 0)
            criteria.add(new LigueTypeCriterion(Importance.valueOf(ligueType.getProgress())));
        if (areaAttractiveness.getProgress() > 0)
            criteria.add(new AreaAttractivenessCriterion(Importance.valueOf(areaAttractiveness.getProgress())));
        if (attractiveness.getProgress() > 0)
            criteria.add(new AttractivenessCriterion(Importance.valueOf(attractiveness.getProgress())));
        return criteria;
    }

    private DiscreteSeekBar.OnProgressChangeListener getOnProgressChangeListener() {
        return new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {

                seekBar.setIndicatorFormatter(Importance.valueOf(seekBar.getProgress()).getImportanceLevel());
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {
                seekBar.setIndicatorFormatter(Importance.valueOf(seekBar.getProgress()).getImportanceLevel());
            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
                seekBar.setIndicatorFormatter(Importance.valueOf(seekBar.getProgress()).getImportanceLevel());
            }
        };
    }

    public void initializeProgressDialog(Context context) {
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setTitle(createSpinnableString("Proszę czekać..."));
        mProgressDialog.setMessage(createSpinnableString("Trwa przetwarzaznie danych."));
        mProgressDialog.setCancelable(false);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.show();
    }

    public void cancelProgressDialog() {
        mProgressDialog.cancel();
    }

    private SpannableString createSpinnableString(String text) {
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new RelativeSizeSpan(2f), 0, spannableString.length(), 0);
//        spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), 0, spannableString.length(), 0);

        return spannableString;
    }

    public String getLocationText() {
        return textView.getText().toString();
    }


    private void hideSoftKeyboard() {
        InputMethodManager inputManager = (InputMethodManager)
                getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

    }
}
