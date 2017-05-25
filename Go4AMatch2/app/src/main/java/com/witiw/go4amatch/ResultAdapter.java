package com.witiw.go4amatch;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.witiw.go4amatch.entities.LigueType;
import com.witiw.go4amatch.entities.SportingEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Patryk on 25.05.2017.
 */

public class ResultAdapter extends ArrayAdapter<SportingEvent> {

    private Activity activity;

    private static LayoutInflater inflater = null;

    public ResultAdapter(Activity activity, List<SportingEvent> events) {
        super(activity, android.R.layout.simple_list_item_1, events);
        this.activity = activity;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int pos, View view, ViewGroup parent) {

        if (view == null)
            view = inflater.inflate(R.layout.result_row, null);

        SportingEvent order = getItem(pos);

        TextView homeTeam = (TextView) view.findViewById(R.id.tvTeamHome);
        TextView awayTeam = (TextView) view.findViewById(R.id.tvTeamAway);
        ImageView image = (ImageView) view.findViewById(R.id.imgLigue);

        if (order.getLigueType().equals(LigueType.CHAMPIONS)) {
            homeTeam.setText("Manchester United");
            awayTeam.setText("Legia Warszawa");
            image.setImageResource(R.drawable.champions_league);
        } else if (order.getLigueType().equals(LigueType.EUROPE)) {
            image.setImageResource(R.drawable.europa_league);
            homeTeam.setText("FC Barcelona");
            awayTeam.setText("Juventus Turyn");
        } else {
            image.setImageResource(R.drawable.other);
            homeTeam.setText("Real Madrid");
            awayTeam.setText("As Monaco");
        }

        return view;
    }

}
