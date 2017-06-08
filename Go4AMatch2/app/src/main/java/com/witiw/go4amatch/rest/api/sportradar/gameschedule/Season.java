package com.witiw.go4amatch.rest.api.sportradar.gameschedule;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by Patryk on 07.06.2017.
 */
@Root(strict = false)
public class Season {

    @Attribute
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
