package com.avocados.skyjourneys.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FlightSearchResponse {

    private FlightDetails[] searchResults;

    @JsonCreator
    public FlightSearchResponse(FlightDetails[] searchResults) {
        this.searchResults = searchResults;
    }

    public FlightSearchResponse(FlightDetails[] searchResults, boolean flipResults) {
        if (flipResults){
            List<FlightDetails> list = Arrays.asList(searchResults);
            Collections.reverse(list);
            searchResults = list.toArray(searchResults);
        }
        this.searchResults = searchResults;
    }

    @JsonProperty
    public FlightDetails[] getSearchResults() {
        return searchResults;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightSearchResponse that = (FlightSearchResponse) o;
        return Arrays.equals(getSearchResults(), that.getSearchResults());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getSearchResults());
    }
}
