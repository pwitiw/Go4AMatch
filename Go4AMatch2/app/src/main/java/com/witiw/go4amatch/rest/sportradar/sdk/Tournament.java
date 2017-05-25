package com.witiw.go4amatch.rest.sportradar.sdk;

/**
 * Created by Patryk on 06.05.2017.
 */

public class Tournament {

    private String id;
    private String name;
    private Sport sport;
    private Category category;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sport getSport() {
        return this.sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
