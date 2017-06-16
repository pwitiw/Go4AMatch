package com.witiw.go4amatch.rest.api.google;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Patryk on 02.06.2017.
 */
@Root(name = "PlaceSearchResponse", strict = false)
public class PlaceSearchResponse {

    @ElementList(inline = true, entry = "result", type = Result.class, required = false)
    private List<Result> results;

//    @Element(required = false, name = "next_page_token")
//    private String nextPageToken;

    @Element(required = false)
    private String next_page_token;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getNextPageToken() {
        return next_page_token;
    }
}
