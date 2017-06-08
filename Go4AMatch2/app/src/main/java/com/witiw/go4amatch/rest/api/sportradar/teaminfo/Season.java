package com.witiw.go4amatch.rest.api.sportradar.teaminfo;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Patryk on 03.06.2017.
 */
@Root(name = "Season", strict = false)
public class Season {

    @Attribute
    private String id;

    @Element(required = false)
    private Form form;

    public String getForm() {
        return form.getTotal();
    }

    public String getId() {
        return id;
    }
}
