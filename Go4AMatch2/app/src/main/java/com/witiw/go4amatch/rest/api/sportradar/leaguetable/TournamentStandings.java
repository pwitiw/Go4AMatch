package com.witiw.go4amatch.rest.api.sportradar.leaguetable;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Patryk on 04.06.2017.
 */
@Root(name = "tournament_standings", strict = false)
public class TournamentStandings {

    @ElementList(type = Standings.class, inline = true)
    private List<Standings> standings;

    public void copy(TournamentStandings tournamentStandings) {
        this.standings = tournamentStandings.standings;
    }

    public List<TeamStanding> getTableList() {
        return standings.get(0).getGroup();
    }

    public int getPosForTeam(String name) {
        List<TeamStanding> teamStandings = standings.get(0).getGroup();
        for (TeamStanding team : teamStandings) {
            if(team.getTeamName().equals(name))
                return team.getRank();
        }
        return -1;
    }
}
