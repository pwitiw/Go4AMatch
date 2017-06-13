package com.witiw.go4amatch.rest.api.sportradar.leaguetable;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patryk on 04.06.2017.
 */
@Root(name = "tournament_standings", strict = false)
public class TournamentStandings {

    @ElementList(inline = true)
    private ArrayList<Standings> standings;

    public int getPosForTeam(String name) {
        List<TeamStanding> teamStandings = standings.get(0).getGroup().get(0).getTeamStandings();
        for (TeamStanding team : teamStandings) {
            if (team.getTeamName().equals(name))
                return team.getRank();
        }
        return -1;
    }
}
