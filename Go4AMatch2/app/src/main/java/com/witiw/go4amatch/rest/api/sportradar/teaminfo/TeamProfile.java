package com.witiw.go4amatch.rest.api.sportradar.teaminfo;

import com.witiw.go4amatch.rest.api.sportradar.Team;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Patryk on 03.06.2017.
 */
@Root(name = "team_profile", strict = false)
public class TeamProfile {

    @Element
    private Venue venue;

    @Element
    private Statistics statistics;

    @Element(required = false)
    private Team team;

    // dodatkowe pole
    private int rank;

    public Statistics getStatistics() {
        return statistics;
    }

    public void copy(TeamProfile teamProfile) {
        venue = teamProfile.venue;
        statistics = teamProfile.getStatistics();
    }

    public String getCity() {
        return venue.getCityName();
    }

    public String getFormForSeason(String seasonId) {
        return statistics.getFormForSeason(seasonId);
    }

    public String getTeamName() {
        return team.getName();
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getCountryCode() {
        return team.getCountryCode();
    }
}
