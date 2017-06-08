package com.witiw.go4amatch.rest.api.sportradar.leaguetable;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Patryk on 04.06.2017.
 */
@Root(name = "standings", strict = false)
public class Standings {

    @ElementList(type = TeamStanding.class)
    private List<TeamStanding> group;

    public List<TeamStanding> getGroup() {
        return group;
    }
}
