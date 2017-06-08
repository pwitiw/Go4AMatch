package com.witiw.go4amatch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.witiw.go4amatch.entities.LeagueType;
import com.witiw.go4amatch.entities.SportingEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patryk on 09.04.2017.
 */

public class ResultFragment extends Fragment {

    MainPresenter presenter;
    ListView lvResults;

    public ResultFragment(MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, container, false);
        lvResults = (ListView) view.findViewById(R.id.lvResults);
        ResultAdapter listAdapter = new ResultAdapter(getActivity(), new ArrayList<SportingEvent>());
        lvResults.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
        return view;
    }


    public List<SportingEvent> getTestData() {
        List<SportingEvent> events = new ArrayList<>();
        SportingEvent sp1 = new SportingEvent();
        sp1.setLeagueType(LeagueType.CHAMPIONS_LEAGUE);
        sp1.getTeams().setAway("Manchester United");
        sp1.getTeams().setHome("Real Madrid");
        events.add(sp1);
        SportingEvent sp2 = new SportingEvent();
        sp2.setLeagueType(LeagueType.EUROPE);
        sp2.getTeams().setAway("Fc Barcelona");
        sp2.getTeams().setHome("Real Madrid");
        events.add(sp2);
        SportingEvent sp3 = new SportingEvent();
        sp3.getTeams().setAway("Manchester United");
        sp3.getTeams().setHome("CHelsea");
        sp3.setLeagueType(LeagueType.ENGLAND);
        events.add(sp3);
        SportingEvent sp4 = new SportingEvent();
        sp4.getTeams().setAway("Manchester United");
        sp4.getTeams().setHome("Ajax Amsterdam");
        sp4.setLeagueType(LeagueType.CHAMPIONS_LEAGUE);
        events.add(sp4);
        return events;
    }

    public void updateData(List<SportingEvent> events) {

        ResultAdapter listAdapter = new ResultAdapter(getActivity(), events);
        lvResults.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
    }
}