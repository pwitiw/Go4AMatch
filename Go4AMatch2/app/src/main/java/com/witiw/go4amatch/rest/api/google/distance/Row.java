package com.witiw.go4amatch.rest.api.google.distance;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Patryk on 06.06.2017.
 */
@Root(strict = false)
public class Row {

    @Element
    private Elem element;

    public int getDistance(){
       return element.getDistance();
    }

}
