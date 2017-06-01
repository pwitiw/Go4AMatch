package com.witiw.go4amatch;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

import com.witiw.go4amatch.logic.ahp.Importance;
import com.witiw.go4amatch.entities.Criterion;
import com.witiw.go4amatch.entities.criterions.AttractivenessCriterion;
import com.witiw.go4amatch.entities.criterions.BudgetCriterion;
import com.witiw.go4amatch.entities.criterions.DistanceCriterion;
import com.witiw.go4amatch.entities.criterions.LigueTypeCriterion;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patryk on 24.05.2017.
 */

public class PreferenceFragment extends Fragment {

    DiscreteSeekBar budget;
    DiscreteSeekBar attractiveness;
    DiscreteSeekBar areaAttractiveness;
    DiscreteSeekBar ligueType;
    DiscreteSeekBar distance;
    Button searchButton;
    IMainPresenter presenter;
    ProgressDialog ps;

    PreferenceFragment(IMainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preferences, container, false);
        searchButton = (Button) view.findViewById(R.id.btSearch);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            criteria.add(new AttractivenessCriterion(Importance.valueOf(areaAttractiveness.getProgress())));
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

}
