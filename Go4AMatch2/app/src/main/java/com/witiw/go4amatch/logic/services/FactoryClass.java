package com.witiw.go4amatch.logic.services;

import com.witiw.go4amatch.entities.CountryBudget;
import com.witiw.go4amatch.entities.GameType;
import com.witiw.go4amatch.entities.LeagueType;
import com.witiw.go4amatch.rest.api.sportradar.Team;
import com.witiw.go4amatch.rest.api.sportradar.gameschedule.SportEvent;
import com.witiw.go4amatch.rest.api.sportradar.leaguetable.TournamentStandings;
import com.witiw.go4amatch.rest.api.sportradar.teaminfo.TeamProfile;
import com.witiw.go4amatch.rest.impl.GoogleRestService;
import com.witiw.go4amatch.rest.impl.SportRadarRestService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Patryk on 09.06.2017.
 */

public class FactoryClass {

    String startingPosition;
    static HashMap<String, Integer> distanceMap = new HashMap<>();
    static HashMap<String, Integer> areaAttractivenessMap = new HashMap<>();
    static Map<String, TeamProfile> teamMap = new HashMap<>();

    public void setStartingPosition(String startingPosition) {
        this.startingPosition = startingPosition;
    }

    public Data getData(SportEvent sportEvent, TournamentStandings tournamentStandings, LeagueType leagueType) throws Exception {

        cacheTeams(sportEvent, tournamentStandings, leagueType);
        cacheDistance(sportEvent);
        cacheAreaAttractiveness(sportEvent);
        return createData(sportEvent, leagueType);
    }

    public Data createData(SportEvent sportEvent, LeagueType leagueType) {
        String season = sportEvent.getSeason();
        Data data = new Data();
        TeamProfile home = teamMap.get(sportEvent.getHomeTeam().getId());
        TeamProfile away = teamMap.get(sportEvent.getAwayTeam().getId());
        data.setHomeTeamName(home.getTeamName());
        data.setHomeTeamForm(home.getFormForSeason(season));
        data.setHomeTeamCityName(home.getCity());
        data.setHomeTeamCountryCode(home.getCountryCode());
        data.setHomeRank(home.getRank());
        data.setAwayTeamName(away.getTeamName());
        data.setAwayTeamForm(away.getFormForSeason(season));
        data.setAwayTeamCityName(away.getCity());
        data.setAwayTeamCountryCode(away.getCountryCode());
        data.setAwayRank(away.getRank());
        data.setBudget(CountryBudget.getBudget(home.getCountryCode(), leagueType));
        data.setPosition(Math.abs(away.getRank() - home.getRank()));
        data.setGameType(sportEvent.getTournamentRound().getType());
        data.setDistance(distanceMap.get(home.getCity()));
        data.setAreaAttractiveness(areaAttractivenessMap.get(home.getCity()));
        data.setLeagueType(leagueType);
        return data;
    }

    private void cacheTeams(SportEvent event, TournamentStandings tournamentTable, LeagueType leagueType) throws Exception {
        List<Team> teamList = event.getCompetitors();
        for (Team team : teamList) {
            if (!teamMap.containsKey(team.getId())) {
                TeamProfile teamInfo = SportRadarRestService.getTeamInfo(team.getId());
                teamInfo.setRank(getRank(teamInfo, leagueType, tournamentTable));
                teamMap.put(team.getId(), teamInfo);
            }
        }
    }

    private void cacheDistance(SportEvent event) throws IOException {
        TeamProfile homeTeam = teamMap.get(event.getHomeTeam().getId());
        String homeCity = homeTeam.getCity();
        if (!distanceMap.containsKey(homeCity)) {
            String destination = homeCity.replace(" ", "+") + "," + homeTeam.getCountryName();
            String start = startingPosition.replace(" ", "+");
            int distance = GoogleRestService.getDistanceBetweenCities(start, destination);
            distanceMap.put(homeCity, distance);
        }
    }

    private void cacheAreaAttractiveness(SportEvent event) throws Exception {
        String homeCity = teamMap.get(event.getHomeTeam().getId()).getCity();
        if (!areaAttractivenessMap.containsKey(homeCity)) {
            int areaAttractiveness = GoogleRestService.getAttractionsForCity(homeCity);
            areaAttractivenessMap.put(homeCity, areaAttractiveness);
        }
    }

    private int getRank(TeamProfile teamInfo, LeagueType leagueType, TournamentStandings tournamentTable) {
        if (LeagueType.CHAMPIONS_LEAGUE == leagueType || LeagueType.EUROPE == leagueType) {
            return 1;
        } else {
            return tournamentTable.getPosForTeam(teamInfo.getTeamName());
        }
    }


    public class Data {

        private String homeTeamName;
        private String homeTeamForm;
        private String homeTeamCityName;
        private String homeTeamCountryCode;
        private int homeRank;
        private String awayTeamName;
        private String awayTeamForm;
        private String awayTeamCityName;
        private String awayTeamCountryCode;
        private int awayRank;
        private double budget;
        private int position;
        private GameType gameType;
        private LeagueType leagueType;
        private int distance;
        private int areaAttractiveness;

        public String getHomeTeamName() {
            return homeTeamName;
        }

        public void setHomeTeamName(String homeTeamName) {
            this.homeTeamName = homeTeamName;
        }

        public String getHomeTeamForm() {
            return homeTeamForm;
        }

        public void setHomeTeamForm(String homeTeamForm) {
            this.homeTeamForm = homeTeamForm;
        }

        public String getAwayTeamName() {
            return awayTeamName;
        }

        public void setAwayTeamName(String awayTeamName) {
            this.awayTeamName = awayTeamName;
        }

        public String getAwayTeamForm() {
            return awayTeamForm;
        }

        public void setAwayTeamForm(String awayTeamForm) {
            this.awayTeamForm = awayTeamForm;
        }

        public double getBudget() {
            return budget;
        }

        public void setBudget(double budget) {
            this.budget = budget;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public GameType getGameType() {
            return gameType;
        }

        public void setGameType(GameType gameType) {
            this.gameType = gameType;
        }

        public LeagueType getLeagueType() {
            return leagueType;
        }

        public void setLeagueType(LeagueType leagueType) {
            this.leagueType = leagueType;
        }

        public int getHomeRank() {
            return homeRank;
        }

        public void setHomeRank(int homeRank) {
            this.homeRank = homeRank;
        }

        public int getAwayRank() {
            return awayRank;
        }

        public void setAwayRank(int awayRank) {
            this.awayRank = awayRank;
        }

        public String getHomeTeamCityName() {
            return homeTeamCityName;
        }

        public void setHomeTeamCityName(String homeTeamCityName) {
            this.homeTeamCityName = homeTeamCityName;
        }

        public String getAwayTeamCityName() {
            return awayTeamCityName;
        }

        public void setAwayTeamCityName(String awayTeamCityName) {
            this.awayTeamCityName = awayTeamCityName;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public int getAreaAttractiveness() {
            return areaAttractiveness;
        }

        public void setAreaAttractiveness(int areaAttractiveness) {
            this.areaAttractiveness = areaAttractiveness;
        }

        public String getHomeTeamCountryCode() {
            return homeTeamCountryCode;
        }

        public void setHomeTeamCountryCode(String homeTeamCountryCode) {
            this.homeTeamCountryCode = homeTeamCountryCode;
        }

        public String getAwayTeamCountryCode() {
            return awayTeamCountryCode;
        }

        public void setAwayTeamCountryCode(String awayTeamCountryCode) {
            this.awayTeamCountryCode = awayTeamCountryCode;
        }
    }
}
