package com.avocados.skyjourneys.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class FlightLeg {
    private final FlightSegment[] segments;

    @JsonCreator
    public FlightLeg(FlightSegment[] segments) {
        this.segments = segments;
    }

    @JsonProperty
    public FlightSegment[] getSegments() {
        return segments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightLeg flightLeg = (FlightLeg) o;
        return Arrays.equals(getSegments(), flightLeg.getSegments());
    }
}
