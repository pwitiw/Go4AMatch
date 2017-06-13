package com.witiw.go4amatch.rest.api.sportradar.leaguetable;

import com.witiw.go4amatch.rest.api.sportradar.Team;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Patryk on 04.06.2017.
 */
@Root(name = "team_standing", strict = false)
public class TeamStanding {

    @Attribute(required = false)
    private int rank;

    @Element(required = false)
    private Team team;

    public int getRank() {
        return rank;
    }

    public String getTeamName() {
        return team.getName();
    }
}
