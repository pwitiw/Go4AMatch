package com.witiw.go4amatch.logic.services;

import com.witiw.go4amatch.entities.GameType;
import com.witiw.go4amatch.entities.LeagueType;

/**
 * Created by Patryk on 04.06.2017.
 */

public class ImportanceService {
    //todo test
    public int getImportance(FactoryClass.Data data) {

        if (isChampionsLeagueAndGroup(data) || areTeamsInTop3(data) || isCup(data))
            return 2;
        else if (isChampionsLeagueAndQualification(data)
                || isEuropeLeagueAndGroup(data)
                || (isTeamInTop3(data.getHomeRank()) || isTeamInTop3(data.getAwayRank())))
            return 1;
        else
            return 0;
    }

    private boolean isEuropeLeagueAndGroup(FactoryClass.Data data) {
        return LeagueType.EUROPE == data.getLeagueType() && GameType.group == data.getGameType();
    }

    private boolean isChampionsLeagueAndGroup(FactoryClass.Data data) {
        return LeagueType.CHAMPIONS_LEAGUE == data.getLeagueType() && GameType.group == data.getGameType();
    }


    private boolean isCup(FactoryClass.Data data) {
        return GameType.cup == data.getGameType();

    }

    private boolean areTeamsInTop3(FactoryClass.Data data) {
        return isTeamInTop3(data.getHomeRank()) && isTeamInTop3(data.getAwayRank());
    }

    private boolean isTeamInTop3(int rank) {
        return rank <= 3;
    }

    private boolean isChampionsLeagueAndQualification(FactoryClass.Data data) {
        return GameType.qualification == data.getGameType() && LeagueType.CHAMPIONS_LEAGUE == data.getLeagueType();
    }

}
