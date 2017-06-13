package com.witiw.go4amatch.logic.services;

import com.witiw.go4amatch.entities.CountryTournamentMap;
import com.witiw.go4amatch.entities.LeagueType;

/**
 * Created by Patryk on 10.06.2017.
 */

public class LeaguePointsService {

    private final double championsLeagueMultiplexer = 1.5;
    private final double europeLeagueMultiplexer = 1.2;

    public double getLeaguePoints(FactoryClass.Data data) {

        if (LeagueType.CHAMPIONS_LEAGUE == data.getLeagueType() || LeagueType.EUROPE == data.getLeagueType()) {
            String tournamentId1 = getTournamentId(data.getHomeTeamCountryCode());
            String tournamentId2 = getTournamentId(data.getAwayTeamCountryCode());
            return computePoints(data, tournamentId1, tournamentId2);
        } else
            return data.getLeagueType().getUefaCoefficient();
    }

    private String getTournamentId(String countryCode) {
        String tournamentId = CountryTournamentMap.map.get(countryCode);
        if (tournamentId == null)
            return LeagueType.OTHER.getSportRadarId();
        return tournamentId;
    }

    private double computePoints(FactoryClass.Data data, String tournament1, String tournament2) {
        double coefficient1 = LeagueType.getCoefficientForSportRadarId(tournament1);
        double coefficient2 = LeagueType.getCoefficientForSportRadarId(tournament2);
        if (LeagueType.CHAMPIONS_LEAGUE == data.getLeagueType())
            return (coefficient1 + coefficient2) / 2.0 * championsLeagueMultiplexer;
        else
            return (coefficient1 + coefficient2) / 2.0 * europeLeagueMultiplexer;
    }
}
