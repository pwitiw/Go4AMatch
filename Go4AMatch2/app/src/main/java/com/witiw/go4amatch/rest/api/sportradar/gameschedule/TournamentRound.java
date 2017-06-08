package com.witiw.go4amatch.rest.api.sportradar.gameschedule;

import com.witiw.go4amatch.entities.GameType;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by Patryk on 04.06.2017.
 */
@Root(name = "tournament_round", strict = false)
public class TournamentRound {

    @Attribute
    private GameType type;

    public GameType getType() {
        return type;
    }
}
