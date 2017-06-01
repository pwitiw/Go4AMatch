package com.witiw.go4amatch.rest.google;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patryk on 02.06.2017.
 */
@Root(name="PlaceSearchResponse",strict = false)
public class PlaceSearchResponse {

    @ElementList(inline = true, entry = "result",  type = Result.class)
    private List<Result> results = new ArrayList();

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}