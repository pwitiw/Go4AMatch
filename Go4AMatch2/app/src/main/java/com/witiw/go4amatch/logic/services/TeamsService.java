package com.witiw.go4amatch.logic.services;

import com.witiw.go4amatch.entities.Teams;
import com.witiw.go4amatch.rest.api.sportradar.gameschedule.SportEvent;
import com.witiw.go4amatch.rest.api.sportradar.teaminfo.TeamProfile;

import java.util.Map;

/**
 * Created by Patryk on 04.06.2017.
 */

public class TeamsService {

    public Teams getTeams(SportEvent sportEvent, Map<String, TeamProfile> teamsMap) {
        TeamProfile homeTeam = teamsMap.get(sportEvent.getHomeTeam().getId());
        TeamProfile awayTeam = teamsMap.get(sportEvent.getAwayTeam().getId());
        Teams teams = new Teams();
        teams.setHome(homeTeam.getTeamName());
        teams.setAway(awayTeam.getTeamName());
        teams.setFormHome(homeTeam.getFormForSeason(sportEvent.getSeason()));
        teams.setFormAway(awayTeam.getFormForSeason(sportEvent.getSeason()));
        return teams;
    }
}
