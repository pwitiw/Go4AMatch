package com.witiw.go4amatch.rest.sportradar.sdk;

/**
 * Created by Patryk on 06.05.2017.
 */

public class TournamentRound {
    private String type;
    private String name;
    private String other_match_id;

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOtherMatchId() {
        return this.other_match_id;
    }

    public void setOtherMatchId(String other_match_id) {
        this.other_match_id = other_match_id;
    }
}
