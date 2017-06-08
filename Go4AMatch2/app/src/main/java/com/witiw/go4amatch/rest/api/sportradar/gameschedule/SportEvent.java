package com.witiw.go4amatch.rest.api.sportradar.gameschedule;

import com.witiw.go4amatch.rest.api.sportradar.Team;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Patryk on 03.06.2017.
 */
@Root(name = "sport_event", strict = false)
public class SportEvent {

    @ElementList(type = Team.class, required = false)
    private List<Team> competitors;

    @Attribute(required = false)
    private String scheduled;

    @Element(name = "tournament_round")
    private TournamentRound tournamentRound;

    @Element(name = "season")
    private Season season;

    public List<Team> getCompetitors() {
        return competitors;
    }

    public String getScheduled() {
        return scheduled;
    }

    public TournamentRound getTournamentRound() {
        return tournamentRound;
    }

    public Team getHomeTeam() {
        return competitors.get(0);
    }

    public Team getAwayTeam() {
        return competitors.get(1);
    }

    public String getSeason(){
        return season.getId();
    }

}

