package com.witiw.go4amatch.rest.api.sportradar;

import com.witiw.go4amatch.rest.api.sportradar.teaminfo.Category;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
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

    @Element(required = false)
    private Category category;

    public String getId() {
        return id;
    }

    public String getQualifier() {
        return qualifier;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return category.getCountryCode();
    }
}
