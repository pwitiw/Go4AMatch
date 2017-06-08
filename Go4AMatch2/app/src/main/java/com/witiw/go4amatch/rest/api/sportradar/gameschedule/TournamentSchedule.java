package com.witiw.go4amatch.rest.api.sportradar.gameschedule;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Patryk on 03.06.2017.
 */
@Root(name = "tournament_schedule", strict = false)
public class TournamentSchedule {

    @ElementList(name = "sport_events", type = SportEvent.class)
    List<SportEvent> sportEvents;

    @Element
    private Tournament tournament;

    public List<SportEvent> getSportEvents() {
        return sportEvents;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void copy(TournamentSchedule tournamentSchedule) {
        this.sportEvents = tournamentSchedule.getSportEvents();
        this.tournament = tournamentSchedule.getTournament();
    }

}
