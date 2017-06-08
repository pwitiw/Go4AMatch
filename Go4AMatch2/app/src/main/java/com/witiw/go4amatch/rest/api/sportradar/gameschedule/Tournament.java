package com.witiw.go4amatch.rest.api.sportradar.gameschedule;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Patryk on 03.06.2017.
 */
@Root(name = "tournament", strict = false)
public class Tournament {

    @Attribute
    private String id;

    @Attribute
    private String name;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
