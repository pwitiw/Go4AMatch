package com.witiw.go4amatch.logic;

import com.witiw.go4amatch.IMainPresenter;
import com.witiw.go4amatch.entities.FormType;
import com.witiw.go4amatch.entities.LeagueType;
import com.witiw.go4amatch.entities.SportingEvent;
import com.witiw.go4amatch.entities.Teams;
import com.witiw.go4amatch.logic.services.DerbyService;
import com.witiw.go4amatch.logic.services.FactoryClass;
import com.witiw.go4amatch.logic.services.FormService;
import com.witiw.go4amatch.logic.services.ImportanceService;
import com.witiw.go4amatch.logic.services.PositionService;
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
import java.util.List;

/**
 * Created by Patryk on 03.06.2017.
 */

public class DataProcessingService {

    private IMainPresenter mainPresenter;
    //SERVICES
    private ImportanceService importanceService;
    private FormService formService;
    private TeamsService teamsService;
    private PositionService positionService;
    private DerbyService derbyService;
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
        factory.setStartingPosition(mainPresenter.getLocation());
        List<SportingEvent> sportingEvents = new ArrayList<>();
        TournamentSchedule tournamentSchedule = SportRadarRestService.getLeagueSchedule(leagueType.getSportRadarId());
        TournamentStandings tournamentTable = SportRadarRestService.getTableLeague(leagueType.getSportRadarId());

        //// FIXME: 06.06.2017  tutaj taki hack zastosujemy, ze bedzie miala tablica tylko z 3 elem, zeby nie zabralo duzo requestow
        for (SportEvent event : tournamentSchedule.getSportEvents().subList(152, 156)) {
            FactoryClass.Data data = factory.getData(event, tournamentTable, leagueType);
            SportingEvent sportingEvent = createSportingEvent(data);
            sportingEvents.add(sportingEvent);
        }
        return sportingEvents;
    }

    private SportingEvent createSportingEvent(FactoryClass.Data data) throws IOException {

        Teams teams = teamsService.getTeams(data);
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
        positionService = new PositionService();
        derbyService = new DerbyService();
    }

}
