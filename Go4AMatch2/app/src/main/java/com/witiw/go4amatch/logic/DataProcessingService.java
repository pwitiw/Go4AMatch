package com.witiw.go4amatch.logic;

import android.util.Pair;

import com.witiw.go4amatch.IMainPresenter;
import com.witiw.go4amatch.entities.FormType;
import com.witiw.go4amatch.entities.LeagueType;
import com.witiw.go4amatch.entities.SportingEvent;
import com.witiw.go4amatch.entities.Teams;
import com.witiw.go4amatch.logic.services.DerbyService;
import com.witiw.go4amatch.logic.services.FactoryClass;
import com.witiw.go4amatch.logic.services.FormService;
import com.witiw.go4amatch.logic.services.ImportanceService;
import com.witiw.go4amatch.logic.services.LeaguePointsService;
import com.witiw.go4amatch.logic.services.TeamsService;
import com.witiw.go4amatch.rest.api.sportradar.gameschedule.SportEvent;
import com.witiw.go4amatch.rest.api.sportradar.gameschedule.TournamentSchedule;
import com.witiw.go4amatch.rest.api.sportradar.leaguetable.TournamentStandings;
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
    private static Map<String, Pair<TournamentSchedule, TournamentStandings>> tournamentMap = new HashMap<>();
    private IMainPresenter mainPresenter;
    //SERVICES
    private ImportanceService importanceService;
    private FormService formService;
    private TeamsService teamsService;
    private DerbyService derbyService;
    private LeaguePointsService leaguePointsService;
    private FactoryClass factory;

    public DataProcessingService(IMainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
        DataReader.setAppContext(mainPresenter.getContext());
        initServices();
    }

    //do całego algorytmu
    public List<SportingEvent> processData() throws Exception {
        List<SportingEvent> events = new ArrayList<>();
        for (LeagueType league : LeagueType.values()) {
            events.addAll(getSportingEventsForLeague(league));
        }
        return events;
    }

    public List<SportingEvent> getSportingEventsForLeague(LeagueType leagueType) throws Exception {
        if (!isLocationSet())
            throw new IllegalArgumentException();
        factory.setStartingPosition(mainPresenter.getLocation());
        List<SportingEvent> sportingEvents = new ArrayList<>();
        cacheTournamentMap(leagueType);

        // FIXME: 06.06.2017  tutaj taki hack zastosujemy, ze bedzie miala tablica tylko z 3 elem, zeby nie zabralo duzo requestow
//        for (SportEvent event : tournamentMap.get(leagueType.getSportRadarId()).first.getSportEvents().subList(178, 181)) {
        for (SportEvent event : tournamentMap.get(leagueType.getSportRadarId()).first.getSportEvents()) {
            FactoryClass.Data data = factory.getData(event, tournamentMap.get(leagueType.getSportRadarId()).second, leagueType);
            SportingEvent sportingEvent = createSportingEvent(data);
            sportingEvents.add(sportingEvent);
        }

        return sportingEvents;
    }

    private SportingEvent createSportingEvent(FactoryClass.Data data) throws IOException {

        Teams teams = teamsService.getTeams(data);

        double leaguePoints = leaguePointsService.getLeaguePoints(data);
        //Attractiveness
        int importance = importanceService.getImportance(data);
        int derby = derbyService.getDerby(data);
        FormType formType = formService.getFormType(teams);

        return new SportingEvent.SportingEventBuilder()
                .withTeams(teams)
                .withDerby(derby)
                .withBudget(data.getBudget())
                .withDistance(data.getDistance())
                .withAreaAttractiveness(data.getAreaAttractiveness())
                .withImportance(importance)
                .withPosition(data.getPosition())
                .withForm(formType)
                .withLeaguePoints(leaguePoints)
                .withLeagueType(data.getLeagueType())
                .build();
    }

    public List<SportingEvent> processDataFromXmlFile() {
        Document testDataDoc = DataReader.readTestData();
        if (testDataDoc == null || testDataDoc == null)
            throw new RuntimeException();
        return XmlPojoConverter.convert(testDataDoc);
    }

    private void initServices() {
        factory = new FactoryClass();
        importanceService = new ImportanceService();
        formService = new FormService();
        teamsService = new TeamsService();
        derbyService = new DerbyService();
        leaguePointsService = new LeaguePointsService();
    }

    private boolean isLocationSet() {
        return !mainPresenter.getLocation().equals("");
    }

    private void cacheTournamentMap(LeagueType leagueType) throws Exception {
        String sportRadarId = leagueType.getSportRadarId();
        if (!tournamentMap.containsKey(sportRadarId)) {
            TournamentSchedule tournamentSchedule = SportRadarRestService.getLeagueSchedule(sportRadarId);
            TournamentStandings tournamentTable = SportRadarRestService.getTableLeague(sportRadarId);
            tournamentMap.put(sportRadarId, new Pair(tournamentSchedule, tournamentTable));
        }
    }
}
