package com.witiw.go4amatch.rest.sportradar.sdk;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Patryk on 06.05.2017.
 */

public class SportEvent {
    private String id;
    private Date scheduled;
    private boolean start_time_tbd;
    private String status;
    private TournamentRound tournament_round;
    private Season season;
    private Tournament tournament;
    private ArrayList<Competitor> competitors;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getScheduled() {
        return this.scheduled;
    }

    public void setScheduled(Date scheduled) {
        this.scheduled = scheduled;
    }

    public boolean getStartTimeTbd() {
        return this.start_time_tbd;
    }

    public void setStartTimeTbd(boolean start_time_tbd) {
        this.start_time_tbd = start_time_tbd;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TournamentRound getTournamentRound() {
        return this.tournament_round;
    }

    public void setTournamentRound(TournamentRound tournament_round) {
        this.tournament_round = tournament_round;
    }

    public Season getSeason() {
        return this.season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Tournament getTournament() {
        return this.tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public ArrayList<Competitor> getCompetitors() {
        return this.competitors;
    }

    public void setCompetitors(ArrayList<Competitor> competitors) {
        this.competitors = competitors;
    }

}
