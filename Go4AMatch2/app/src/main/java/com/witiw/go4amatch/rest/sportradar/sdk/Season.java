package com.witiw.go4amatch.rest.sportradar.sdk;

/**
 * Created by Patryk on 06.05.2017.
 */

public class Season {
    private String id;
    private String name;
    private String start_date;
    private String end_date;
    private String year;
    private String tournament_id;

    public String getId() { return this.id; }

    public void setId(String id) { this.id = id; }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    public String getStartDate() { return this.start_date; }

    public void setStartDate(String start_date) { this.start_date = start_date; }

    public String getEndDate() { return this.end_date; }

    public void setEndDate(String end_date) { this.end_date = end_date; }

    public String getYear() { return this.year; }

    public void setYear(String year) { this.year = year; }

    public String getTournamentId() { return this.tournament_id; }

    public void setTournamentId(String tournament_id) { this.tournament_id = tournament_id; }
}
