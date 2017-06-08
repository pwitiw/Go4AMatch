package com.witiw.go4amatch.rest.api.google.distance;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Patryk on 06.06.2017.
 */
@Root(strict = false)
public class Distance {

    @Element
    private int value;

    public int getValue() {
        return value;
    }
}
