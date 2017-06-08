package com.witiw.go4amatch.logic;

import com.witiw.go4amatch.IMainPresenter;
import com.witiw.go4amatch.entities.FormType;
import com.witiw.go4amatch.entities.LeagueType;
import com.witiw.go4amatch.entities.SportingEvent;
import com.witiw.go4amatch.entities.Teams;
import com.witiw.go4amatch.logic.services.AreaAttractivenessService;
import com.witiw.go4amatch.logic.services.BudgetService;
import com.witiw.go4amatch.logic.services.DerbyService;
import com.witiw.go4amatch.logic.services.DistanceService;
import com.witiw.go4amatch.logic.services.FormService;
import com.witiw.go4amatch.logic.services.ImportanceService;
import com.witiw.go4amatch.logic.services.PositionService;
import com.witiw.go4amatch.logic.services.TeamsService;
import com.witiw.go4amatch.rest.api.sportradar.Team;
import com.witiw.go4amatch.rest.api.sportradar.gameschedule.SportEvent;
import com.witiw.go4amatch.rest.api.sportradar.gameschedule.TournamentSchedule;
import com.witiw.go4amatch.rest.api.sportradar.leaguetable.TournamentStandings;
import com.witiw.go4amatch.rest.api.sportradar.teaminfo.TeamProfile;
import com.witiw.go4amatch.rest.impl.SportRadarRestService;
import com.witiw.go4amatch.utils.DataReader;
import com.witiw.go4amatch.utils.XmlPojoConverter;

import org.w3c.dom.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Patryk on 03.06.2017.
 */

public class DataProcessingService {

    private IMainPresenter mainPresenter;
    private ImportanceService importanceService;
    private FormService formService;
    private TeamsService teamsService;
    private PositionService positionService;
    private DerbyService derbyService;
    private AreaAttractivenessService areaAttractivenessService;
    private BudgetService budgetService;
    private DistanceService distanceService;

    private boolean isChampionsLeague;

    public DataProcessingService(IMainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
        DataReader.setAppContext(mainPresenter.getContext());
        initServices();
    }

    public List<SportingEvent> processData() throws Exception {
        List<SportingEvent> events = new ArrayList<>();
        Map<String, TeamProfile> teamsMap = new HashMap<>();
        for (LeagueType league : LeagueType.values()) {
            events.addAll(getSportingEventsForLeague(teamsMap, league));
        }
        return events;
    }

    public List<SportingEvent> getSportingEventsForLeague(Map<String, TeamProfile> teamsMap, LeagueType leagueType) throws Exception {
        HashMap<String,Double> distanceMap = new HashMap<>();
        isChampionsLeague = LeagueType.CHAMPIONS_LEAGUE.equals(leagueType);
        List<SportingEvent> sportingEvents = new ArrayList<>();
        TournamentSchedule tournamentSchedule = SportRadarRestService.getLeagueSchedule(leagueType.getSportRadarId());
        TournamentStandings table = SportRadarRestService.getTableLeague(leagueType.getSportRadarId());

        //// FIXME: 06.06.2017  tutaj taki hack zastosujemy, ze bedzie miala tablica tylko z 3 elem, zeby nie zabralo duzo requestow
        for (SportEvent event : tournamentSchedule.getSportEvents().subList(152, 156)) {
            addTeamsToMap(event, teamsMap, table);
            int budget = budgetService.getBudget();
            int areaAttractiveness = areaAttractivenessService.getAreaAttractiveness();
            double distance = distanceService.getDistance(mainPresenter.getLocation(), event);
            Teams teams = teamsService.getTeams(event, teamsMap);
            //Attractiveness
            int importance = importanceService.getImportance(event, teamsMap, leagueType);
            int position = positionService.getPosition(event, teamsMap);
            int derby = derbyService.getDerby(event, teamsMap);
            FormType formType = formService.getFormType(teams);
            SportingEvent sportingEvent = new SportingEvent.SportingEventBuilder()
                    .withTeams(teams)
                    .withDerby(derby)
                    .withBudget(budget)
                    .withDistance(distance)
                    .withAreaAttractiveness(areaAttractiveness)
                    .withImportance(importance)
                    .withPosition(position)
                    .withForm(formType)
                    .withLeagueType(leagueType)
                    .build();
            sportingEvents.add(sportingEvent);
        }
        return sportingEvents;
    }

    private void addTeamsToMap(SportEvent event, Map<String, TeamProfile> teamMap, TournamentStandings table) throws Exception {
        List<Team> teamList = event.getCompetitors();
        for (Team team : teamList) {
            if (!teamMap.containsKey(team.getId())) {
                TeamProfile teamInfo = SportRadarRestService.getTeamInfo(team.getId());
                teamInfo.setRank(isChampionsLeague ? 1 : table.getPosForTeam(teamInfo.getTeamName()));
                teamMap.put(team.getId(), teamInfo);
            }
        }
    }

    public List<SportingEvent> processDataFromXmlFile() {
        Document testDataDoc = DataReader.readTestData();
        if (testDataDoc == null || testDataDoc == null)
            throw new RuntimeException();
        return XmlPojoConverter.convert(testDataDoc);
    }

    private void initServices() {
        importanceService = new ImportanceService();
        formService = new FormService();
        teamsService = new TeamsService();
        positionService = new PositionService();
        derbyService = new DerbyService();
        areaAttractivenessService = new AreaAttractivenessService();
        budgetService = new BudgetService();
        distanceService = new DistanceService();
    }

}
