package com.witiw.go4amatch.logic.services;

import com.witiw.go4amatch.rest.api.sportradar.gameschedule.SportEvent;
import com.witiw.go4amatch.rest.api.sportradar.teaminfo.TeamProfile;

import java.util.Map;

/**
 * Created by Patryk on 04.06.2017.
 */

public class PositionService {
//todo test
    public int getPosition(SportEvent event,Map<String, TeamProfile> teamsMap) {

        String team1 = event.getHomeTeam().getId();
        String team2 = event.getAwayTeam().getId();
        return Math.abs(teamsMap.get(team1).getRank() - teamsMap.get(team2).getRank());
    }

}
