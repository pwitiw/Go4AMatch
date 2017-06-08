package com.witiw.go4amatch.entities;

/**
 * Created by Patryk on 16.05.2017.
 */
public class SportingEvent {
    private double result;
    Teams teams;
    private int budget;
    private double distance;
    private LeagueType leagueType;
    private int areaAttractiveness;
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
        this.leagueType = sportingEvent.leagueType;
        this.areaAttractiveness = sportingEvent.areaAttractiveness;
        this.attractiveness = sportingEvent.attractiveness;
    }

    public SportingEvent(Teams teams, int budget, double distance, LeagueType leagueType, int areaAttractiveness, Attractiveness attractiveness) {

        this.teams = teams;
        this.budget = budget;
        this.distance = distance;
        this.leagueType = leagueType;
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

    public LeagueType getLeagueType() {
        return leagueType;
    }

    public void setLeagueType(LeagueType leagueType) {
        this.leagueType = leagueType;
    }

    public int getAreaAttractiveness() {
        return areaAttractiveness;
    }

    public void setAreaAttractiveness(int areaAttractiveness) {
        this.areaAttractiveness = areaAttractiveness;
    }

    public Attractiveness getAttractiveness() {
        return attractiveness;
    }

    public void setAttractiveness(Attractiveness attractiveness) {
        this.attractiveness = attractiveness;
    }


    public static class SportingEventBuilder {
        Teams teams;
        private int budget;
        private double distance;
        private LeagueType leagueType;
        private int areaAttractiveness;
        private Attractiveness attractiveness;

        public SportingEventBuilder() {
            attractiveness = new Attractiveness();
        }


        public SportingEventBuilder withTeams(Teams teams) {
            this.teams = teams;
            return this;
        }

        public SportingEventBuilder withBudget(int budget) {
            this.budget = budget;
            return this;
        }

        public SportingEventBuilder withAreaAttractiveness(int areaAttractiveness) {
            this.areaAttractiveness = areaAttractiveness;
            return this;
        }

        public SportingEventBuilder withLeagueType(LeagueType leagueType) {
            this.leagueType = leagueType;
            return this;
        }


        public SportingEventBuilder withDistance(double distance) {
            this.distance = distance;
            return this;
        }

        public SportingEventBuilder withImportance(int importance) {
            this.attractiveness.setImportance(importance);
            return this;
        }

        public SportingEventBuilder withPosition(int position) {
            attractiveness.setPosition(position);
            return this;
        }

        public SportingEventBuilder withDerby(int derby) {
            attractiveness.setDerby(derby);
            return this;
        }

        public SportingEventBuilder withForm(FormType form) {
            attractiveness.setFormType(form);
            return this;
        }

        public SportingEvent build() {
            return new SportingEvent(teams, budget, distance, leagueType, areaAttractiveness, attractiveness);
        }


    }

}
