package com.witiw.go4amatch.rest.api.sportradar.leaguetable;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Patryk on 11.06.2017.
 */
@Root(strict = false)
public class Group {

    @ElementList(name = "team_standing", inline = true)
    List<TeamStanding> teamStandings;

    public List<TeamStanding> getTeamStandings() {
        return teamStandings;
    }
}
