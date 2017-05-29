package com.witiw.go4amatch.entities;

/**
 * Created by Patryk on 16.05.2017.
 */
public class SportingEvent {
    Teams teams;
    private int budget;
    private double distance;
    private LigueType ligueType;
    private double communicationConnection;
    private double areaAttractiveness;
    private Attractiveness attractiveness;

    public SportingEvent() {
        teams = new Teams();
    }


    public Teams getTeams() {
        return teams;
    }

    public SportingEvent(SportingEvent sportingEvent) {
        this.budget = sportingEvent.budget;
        this.distance = sportingEvent.distance;
        this.ligueType = sportingEvent.ligueType;
        this.communicationConnection = sportingEvent.communicationConnection;
        this.areaAttractiveness = sportingEvent.areaAttractiveness;
        this.attractiveness = sportingEvent.attractiveness;
    }

    public SportingEvent(int budget, double distance, LigueType ligueType, double communicationConnection, double areaAttractiveness, Attractiveness attractiveness) {
        this.budget = budget;
        this.distance = distance;
        this.ligueType = ligueType;
        this.communicationConnection = communicationConnection;
        this.areaAttractiveness = areaAttractiveness;
        this.attractiveness = attractiveness;
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

    public double getCommunicationConnection() {
        return communicationConnection;
    }

    public void setCommunicationConnection(double communicationConnection) {
        this.communicationConnection = communicationConnection;
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
