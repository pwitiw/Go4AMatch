package com.witiw.go4amatch.rest.google;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Patryk on 02.06.2017.
 */
@Root(name="Result",strict = false)
public class Result {

    @Element
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
