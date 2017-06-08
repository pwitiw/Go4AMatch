package com.witiw.go4amatch.rest.api.sportradar.teaminfo;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Patryk on 03.06.2017.
 */
@Root(name = "statistics")
public class Statistics {

    @ElementList(type = Season.class)
    private List<Season> seasons;

    public String getFormForSeason(String seasonId) {
        for (int i = seasons.size() - 1; i > 0; i--) {
            if (seasons.get(i).getId().equals(seasonId))
                return seasons.get(i).getForm();
        }
        throw new NullPointerException();
    }
}
