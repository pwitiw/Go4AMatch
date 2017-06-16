package com.witiw.go4amatch.entities;

import java.util.Comparator;

/**
 * Created by Patryk on 16.05.2017.
 */
public class SportingEvent {
    private double result;
    Teams teams;
    private double budget;
    private double distance;
    private double leaguePoints;
    private int areaAttractiveness;
    private Attractiveness attractiveness;
    private LeagueType leagueType;

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
        this.leaguePoints = sportingEvent.leaguePoints;
        this.areaAttractiveness = sportingEvent.areaAttractiveness;
        this.attractiveness = sportingEvent.attractiveness;
    }

    public SportingEvent(Teams teams, double budget, double distance, double leaguePoints, LeagueType leagueType, int areaAttractiveness, Attractiveness attractiveness) {

        this.teams = teams;
        this.budget = budget;
        this.distance = distance;
        this.leaguePoints = leaguePoints;
        this.areaAttractiveness = areaAttractiveness;
        this.attractiveness = attractiveness;
        this.leagueType = leagueType;
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

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(double leaguePoints) {
        this.leaguePoints = leaguePoints;
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

    public LeagueType getLeagueType() {
        return leagueType;
    }

    public void setLeagueType(LeagueType leagueType) {
        this.leagueType = leagueType;
    }


    public static class SportingEventBuilder {
        Teams teams;
        private double budget;
        private double distance;
        private double leaguePoints;
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

        public SportingEventBuilder withBudget(double budget) {
            this.budget = budget;
            return this;
        }

        public SportingEventBuilder withAreaAttractiveness(int areaAttractiveness) {
            this.areaAttractiveness = areaAttractiveness;
            return this;
        }

        public SportingEventBuilder withLeaguePoints(double leaguePoints) {
            this.leaguePoints = leaguePoints;
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
            return new SportingEvent(teams, budget, distance, leaguePoints, leagueType, areaAttractiveness, attractiveness);
        }
    }

    public static Comparator<SportingEvent> Comparator = new Comparator<SportingEvent>() {

        @Override
        public int compare(SportingEvent o1, SportingEvent o2) {
            return (-1) * Double.valueOf(o1.getResult()).compareTo(o2.getResult());
        }

    };

    public String toFormShortString() {
        return "& " + teams.getHome() + "    & " + teams.getAway() + "    & " + leagueType.kindOfSport() + "\\\\\n";
    }

    public String toFormLongString() {
        return "& " + teams.getHome() + "    & " + teams.getAway() + "    & " + (int)(getAttractiveness().getValue()*100) + "\\%    & " + (int) distance / 1000 + "km    & " + leagueType.kindOfSport() + "\\\\\n";
    }

    public String toVersusString() {
        return teams.getHome() + " - " + teams.getAway() + "\n";
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("<!-- ATRAKCYJNOSC -> \t" + attractiveness.getValue() + " -->\n");
        builder.append("<SportingEvent home=\"" + teams.getHome() + "\" away=\"" + teams.getAway() + "\">\n");
        builder.append("<Attractiveness>\n");
        builder.append("<FormHome>" + teams.getFormHome() + "</FormHome>\n");
        builder.append("<FormAway>" + teams.getFormAway() + "</FormAway>\n");
        builder.append("<Importance>" + attractiveness.getImportance() + "</Importance>\n");
        builder.append("<Position>" + attractiveness.getPosition() + "</Position>\n");
        builder.append("<Derby>" + attractiveness.getIsDerby() + "</Derby>\n");
        builder.append("</Attractiveness>\n");
        builder.append("<Budget>" + budget + "</Budget >\n");
        builder.append("<AreaAttractiveness>" + areaAttractiveness + "</AreaAttractiveness>\n");
        builder.append("<LeagueType>" + leagueType.getLeagueName() + "</LeagueType>\n");
        builder.append("<LeaguePoints>" + leaguePoints + "</LeaguePoints>\n");
        builder.append("<Distance>" + distance + "</Distance>\n");
        builder.append("</SportingEvent>\n");
        return builder.toString();
    }
}
