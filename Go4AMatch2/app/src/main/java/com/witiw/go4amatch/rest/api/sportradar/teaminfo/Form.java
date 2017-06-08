package com.witiw.go4amatch.rest.api.sportradar.teaminfo;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by Patryk on 03.06.2017.
 */
@Root(name = "form", strict = false)
public class Form {

    @Attribute
    private String total;

    public String getTotal() {
        return total;
    }
}
