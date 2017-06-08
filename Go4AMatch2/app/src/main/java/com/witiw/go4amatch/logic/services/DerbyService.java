package com.witiw.go4amatch.logic.services;

import android.util.Pair;

import com.witiw.go4amatch.entities.DerbyList;
import com.witiw.go4amatch.rest.api.sportradar.gameschedule.SportEvent;
import com.witiw.go4amatch.rest.api.sportradar.teaminfo.TeamProfile;

import java.util.Map;

/**
 * Created by Patryk on 05.06.2017.
 */

public class DerbyService {

    public int getDerby(SportEvent sportEvent, Map<String, TeamProfile> teamsMap) {
        TeamProfile team1 = teamsMap.get(sportEvent.getHomeTeam().getId());
        TeamProfile team2 = teamsMap.get(sportEvent.getAwayTeam().getId());
        if (team1.getCity().equals(team2.getCity()) || DerbyList.contains(team1.getTeamName(), team2.getTeamName()))
            return 1;
        return 0;
    }


}
