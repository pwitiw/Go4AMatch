package com.witiw.go4amatch.rest.api.sportradar;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by Patryk on 03.06.2017.
 */
@Root(name = "team", strict = false)
public class Team {

    @Attribute
    private String id;

    @Attribute(required = false)
    private String qualifier;

    @Attribute(required = false)
    private String name;

    public String getId() {
        return id;
    }

    public String getQualifier() {
        return qualifier;
    }

    public String getName() {
        return name;
    }
}
