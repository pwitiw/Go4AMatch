package com.witiw.go4amatch.logic.services;

import com.witiw.go4amatch.entities.Teams;

/**
 * Created by Patryk on 04.06.2017.
 */

public class TeamsService {

    public Teams getTeams(FactoryClass.Data data) {
        Teams teams = new Teams();
        teams.setHome(data.getHomeTeamName());
        teams.setAway(data.getAwayTeamName());
        teams.setFormHome(data.getHomeTeamForm());
        teams.setFormAway(data.getAwayTeamForm());
        return teams;
    }
}
