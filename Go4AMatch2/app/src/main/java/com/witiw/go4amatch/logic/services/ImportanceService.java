package com.witiw.go4amatch.logic.services;

import com.witiw.go4amatch.entities.GameType;
import com.witiw.go4amatch.entities.LeagueType;
import com.witiw.go4amatch.rest.api.sportradar.gameschedule.SportEvent;
import com.witiw.go4amatch.rest.api.sportradar.teaminfo.TeamProfile;

import java.util.Map;

/**
 * Created by Patryk on 04.06.2017.
 */

public class ImportanceService {
    //todo test
    public int getImportance(SportEvent sportEvent, Map<String, TeamProfile> teamsMap, LeagueType leagueType) {
        TeamProfile home = teamsMap.get(sportEvent.getHomeTeam().getId());
        TeamProfile away = teamsMap.get(sportEvent.getAwayTeam().getId());

        if (isChampionsLeagueAndGroup(leagueType, sportEvent) || areTeamsInTop3(home, away) || isCup(sportEvent))
            return 2;
        else if (isChampionsLeagueAndQualification(leagueType, sportEvent)
                || isEuropeLeagueAndGroup(leagueType, sportEvent)
                || (isTeamInTop3(home) || isTeamInTop3(away)))
            return 1;
        else
            return 0;
    }

    private boolean isEuropeLeagueAndGroup(LeagueType leagueType, SportEvent sportEvent) {
        return leagueType.getLeagueName().equals(LeagueType.EUROPE) && sportEvent.getTournamentRound().getType().equals(GameType.group);
    }

    private boolean isChampionsLeagueAndGroup(LeagueType leagueType, SportEvent sportEvent) {
        return leagueType.getLeagueName().equals(LeagueType.CHAMPIONS_LEAGUE)
                && sportEvent.getTournamentRound().getType().equals(GameType.group);
    }


    private boolean isCup(SportEvent sportEvent) {
        return sportEvent.getTournamentRound().getType().equals(GameType.cup);

    }

    private boolean areTeamsInTop3(TeamProfile teamProfile1, TeamProfile teamProfile2) {
        return isTeamInTop3(teamProfile1) && isTeamInTop3(teamProfile2);
    }

    private boolean isTeamInTop3(TeamProfile teamProfile) {
        return teamProfile.getRank() <= 3;
    }

    private boolean isChampionsLeagueAndQualification(LeagueType leagueType, SportEvent sportEvent) {
        return sportEvent.getTournamentRound().getType().equals(GameType.qualification)
                && LeagueType.CHAMPIONS_LEAGUE.equals(leagueType);
    }

}
