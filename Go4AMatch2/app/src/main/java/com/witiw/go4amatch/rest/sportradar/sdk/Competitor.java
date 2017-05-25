package com.witiw.go4amatch.rest.sportradar.sdk;

/**
 * Created by Patryk on 06.05.2017.
 */

public class Competitor {
    private String id;
    private String name;
    private String country;
    private String country_code;
    private String abbreviation;
    private String qualifier;

    public String getId() { return this.id; }

    public void setId(String id) { this.id = id; }


    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }


    public String getCountry() { return this.country; }

    public void setCountry(String country) { this.country = country; }

    public String getCountryCode() { return this.country_code; }

    public void setCountryCode(String country_code) { this.country_code = country_code; }


    public String getAbbreviation() { return this.abbreviation; }

    public void setAbbreviation(String abbreviation) { this.abbreviation = abbreviation; }

    public String getQualifier() { return this.qualifier; }

    public void setQualifier(String qualifier) { this.qualifier = qualifier; }
}
