package com.witiw.go4amatch.rest.api.sportradar.gameschedule;

import com.witiw.go4amatch.rest.api.sportradar.Team;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Patryk on 03.06.2017.
 */
@Root(name = "result", strict = false)
public class Result {

    @Element(name = "sport_event")
    private SportEvent sportEvent;

    public List<Team> getCompetitors() {
        return sportEvent.getCompetitors();
    }

    public SportEvent getSportEvent() {
        return sportEvent;
    }

}
