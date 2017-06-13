package com.witiw.go4amatch.logic.services;

import com.witiw.go4amatch.entities.DerbyList;

/**
 * Created by Patryk on 05.06.2017.
 */
public class DerbyService {

    public int getDerby(FactoryClass.Data data) {
        if (data.getHomeTeamCityName().equals(data.getAwayTeamCityName()) || DerbyList.contains(data.getHomeTeamName(), data.getAwayTeamName()))
            return 1;
        return 0;
    }


}
