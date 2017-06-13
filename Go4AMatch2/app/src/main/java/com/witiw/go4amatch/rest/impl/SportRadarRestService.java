package com.witiw.go4amatch.rest.impl;

import com.witiw.go4amatch.entities.SportingEvent;
import com.witiw.go4amatch.rest.api.RestServiceFactory;
import com.witiw.go4amatch.rest.api.SportRadarAPI;
import com.witiw.go4amatch.rest.api.sportradar.gameschedule.TournamentSchedule;
import com.witiw.go4amatch.rest.api.sportradar.leaguetable.TournamentStandings;
import com.witiw.go4amatch.rest.api.sportradar.teaminfo.TeamProfile;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Patryk on 02.06.2017.
 */

public class SportRadarRestService {

    private static SportRadarAPI sportRadarAPI = RestServiceFactory.getSportRadarAPI(SportRadarAPI.class);

    public static int SPORT_RADAR_REST_SERVICE_COUNTER = 0;

    // date format (yyyy-mm-dd)
    public static List<SportingEvent> getSportingEvents(String date) {
        SPORT_RADAR_REST_SERVICE_COUNTER++;
        sportRadarAPI.getDailySchedule(date).enqueue(new Callback<String>() {

            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {
                if (!response.isSuccess())
                    return;
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        return new ArrayList<SportingEvent>();
    }

    public static TournamentSchedule getLeagueSchedule(String leagueId) throws Exception {
        SPORT_RADAR_REST_SERVICE_COUNTER++;

        Response<TournamentSchedule> response = sportRadarAPI.getLeagueSchedule(leagueId).execute();
        Thread.sleep(500);
        return response.body();
    }

    public static TeamProfile getTeamInfo(final String teamId) throws Exception {
        SPORT_RADAR_REST_SERVICE_COUNTER++;
        Response<TeamProfile> response = sportRadarAPI.getTeamInfo(teamId).execute();
        Thread.sleep(1000);
        return response.body();
    }

    public static TournamentStandings getTableLeague(String leagueId) throws Exception {
        SPORT_RADAR_REST_SERVICE_COUNTER++;
        Response<TournamentStandings> response = sportRadarAPI.getTableLeague(leagueId).execute();
        Thread.sleep(500);
        return response.body();
    }
}