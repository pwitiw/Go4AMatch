package com.witiw.go4amatch.rest.api;

import com.witiw.go4amatch.rest.api.sportradar.leaguetable.TournamentStandings;
import com.witiw.go4amatch.rest.api.sportradar.teaminfo.TeamProfile;
import com.witiw.go4amatch.rest.api.sportradar.gameschedule.TournamentSchedule;
import com.witiw.go4amatch.utils.AppProperties;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;


/**
 * Created by Patryk on 05.05.2017.
 */

public interface SportRadarAPI {

    @GET(AppProperties.BASE_SPORTRADAR_URL+"schedules/{date}/schedule." + AppProperties.JSON_DOC_TYPE + "?api_key=" + AppProperties.SPORT_RADAR_API_KEY)
    Call<String> getDailySchedule(@Path("date") String date);

    @GET(AppProperties.BASE_SPORTRADAR_URL+"tournaments/{leagueId}/schedule." + AppProperties.XML_DOC_TYPE + "?api_key=" + AppProperties.SPORT_RADAR_API_KEY)
    Call<TournamentSchedule> getLeagueSchedule(@Path("leagueId") String leagueId);

    @GET(AppProperties.BASE_SPORTRADAR_URL+"teams/{teamId}/profile." + AppProperties.XML_DOC_TYPE + "?api_key=" + AppProperties.SPORT_RADAR_API_KEY)
    Call<TeamProfile> getTeamInfo(@Path("teamId") String teamId);

    @GET(AppProperties.BASE_SPORTRADAR_URL+"tournaments/{leagueId}/standings." + AppProperties.XML_DOC_TYPE + "?api_key=" + AppProperties.SPORT_RADAR_API_KEY)
    Call<TournamentStandings> getTableLeague(@Path("leagueId") String leagueId);
}
