package com.witiw.go4amatch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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

    public void updateData(List<SportingEvent> events) {
        ResultAdapter listAdapter = new ResultAdapter(getActivity(), events);
        lvResults.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
    }
}