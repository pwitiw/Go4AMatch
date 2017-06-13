package com.witiw.go4amatch.rest.api.sportradar.teaminfo;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by Patryk on 03.06.2017.
 */
@Root(name = "venue", strict = false)
public class Venue {

    @Attribute(name = "city_name")
    private String cityName;

    @Attribute(name = "map_coordinates", required = false)
    private double[] mapCoordinates;

    @Attribute(name = "country_name")
    private String countryName;

    public String getCityName() {
        return cityName;
    }

    public double[] getMapCoordinates() {
        return mapCoordinates;
    }

    public String getCountryName() {
        return countryName;
    }
}
