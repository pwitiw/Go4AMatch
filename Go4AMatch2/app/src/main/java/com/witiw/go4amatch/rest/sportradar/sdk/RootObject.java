package com.witiw.go4amatch.rest.sportradar.sdk;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Patryk on 06.05.2017.
 */

public class RootObject {
    private Date generated_at;
    private String schema;
    private ArrayList<SportEvent> sport_events;

    public Date getGeneratedAt() {
        return this.generated_at;
    }

    public void setGeneratedAt(Date generated_at) {
        this.generated_at = generated_at;
    }

    public String getSchema() {
        return this.schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public ArrayList<SportEvent> getSportEvents() {
        return this.sport_events;
    }

    public void setSportEvents(ArrayList<SportEvent> sport_events) {
        this.sport_events = sport_events;
    }
}

