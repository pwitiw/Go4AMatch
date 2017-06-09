package com.witiw.go4amatch.rest.api.sportradar.teaminfo;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by Patryk on 09.06.2017.
 */
@Root(strict = false)
public class Category {

    @Attribute(name = "country_code")
    private String countryCode;

    public String getCountryCode() {
        return countryCode;
    }
}
