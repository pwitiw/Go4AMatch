package com.witiw.go4amatch.entities;

/**
 * Created by Patryk on 16.05.2017.
 */
public class SportingEvent {
    private double result;
    Teams teams;
    private int budget;
    private double distance;
    private LigueType ligueType;
    private double areaAttractiveness;
    private Attractiveness attractiveness;

    public SportingEvent() {
        attractiveness = new Attractiveness();
        teams = new Teams();
    }


    public Teams getTeams() {
        return teams;
    }

    public SportingEvent(SportingEvent sportingEvent) {
        this.budget = sportingEvent.budget;
        this.distance = sportingEvent.distance;
        this.ligueType = sportingEvent.ligueType;
        this.areaAttractiveness = sportingEvent.areaAttractiveness;
        this.attractiveness = sportingEvent.attractiveness;
    }

    public SportingEvent(int budget, double distance, LigueType ligueType, double communicationConnection, double areaAttractiveness, Attractiveness attractiveness) {
        this.budget = budget;
        this.distance = distance;
        this.ligueType = ligueType;
        this.areaAttractiveness = areaAttractiveness;
        this.attractiveness = attractiveness;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public void setTeams(Teams teams) {
        this.teams = teams;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public LigueType getLigueType() {
        return ligueType;
    }

    public void setLigueType(LigueType ligueType) {
        this.ligueType = ligueType;
    }

    public double getAreaAttractiveness() {
        return areaAttractiveness;
    }

    public void setAreaAttractiveness(double areaAttractiveness) {
        this.areaAttractiveness = areaAttractiveness;
    }

    public Attractiveness getAttractiveness() {
        return attractiveness;
    }

    public void setAttractiveness(Attractiveness attractiveness) {
        this.attractiveness = attractiveness;
    }


}
