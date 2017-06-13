package com.witiw.go4amatch.rest.api.google.distance;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Patryk on 06.06.2017.
 */
@Root(strict = false)
public class Elem {
    @Element(required = false)
    private Distance distance;

    public int getDistance() {
        if (distance == null)
            return 99999999;
        return distance.getValue();
    }
}
