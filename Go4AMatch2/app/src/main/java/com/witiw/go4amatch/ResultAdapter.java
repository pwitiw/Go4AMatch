package com.witiw.go4amatch;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.witiw.go4amatch.entities.LeagueType;
import com.witiw.go4amatch.entities.SportingEvent;

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

        SportingEvent event = getItem(pos);
        //todo testy
        TextView leagueType = (TextView) view.findViewById(R.id.tvLeagueType);
        leagueType.setText(event.getLeagueType().getLeagueName());

        TextView homeTeam = (TextView) view.findViewById(R.id.tvTeamHome);
        TextView awayTeam = (TextView) view.findViewById(R.id.tvTeamAway);
        ImageView image = (ImageView) view.findViewById(R.id.imgLigue);

        if (event.getLeagueType().equals(LeagueType.CHAMPIONS_LEAGUE)) {
            image.setImageResource(R.drawable.champions_league);
        } else if (event.getLeagueType().equals(LeagueType.EUROPE)) {
            image.setImageResource(R.drawable.europa_league);
        } else if (event.getLeagueType().equals(LeagueType.CHAMPIONS_LEAGUE_VOLLEYBALL)) {
            image.setImageResource(R.drawable.championsleague_volleyball);
        } else if (event.getLeagueType().equals(LeagueType.CHAMPIONS_LEAGUE_HANDBALL)) {
            image.setImageResource(R.drawable.handball_championsleague);
        }else if (event.getLeagueType().equals(LeagueType.EUROLIGA)) {
            image.setImageResource(R.drawable.championsleague_basketball);
        } else {
            image.setImageResource(R.drawable.other);
        }

        homeTeam.setText(event.getTeams().getHome());
        awayTeam.setText(event.getTeams().getAway());
        return view;
    }

}
