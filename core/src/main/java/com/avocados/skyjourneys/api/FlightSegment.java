package com.avocados.skyjourneys.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class FlightSegment {
    //General Details
    private final String airline;
    private final String flightNumber;
    private final String flightLength;

    //Arrival details
    private final String arrivalDateTime;
    private final String arrivalAirportIATA;
    private final String arrivalAirportName;
    private final String arrivalAirportTerminal;

    //Departure details
    private final String departureDateTime;
    private final String departureAirportIATA;
    private final String departureAirportName;
    private final String departureAirportTerminal;

    @JsonCreator
    public FlightSegment(String airline, String flightNumber, String flightLength, String arrivalDateTime, String arrivalAirportIATA, String arrivalAirportName, String arrivalAirportTerminal, String departureDateTime, String departureAirportIATA, String departureAirportName, String departureAirportTerminal) {
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.flightLength = flightLength;
        this.arrivalDateTime = arrivalDateTime;
        this.arrivalAirportIATA = arrivalAirportIATA;
        this.arrivalAirportName = arrivalAirportName;
        this.arrivalAirportTerminal = arrivalAirportTerminal;
        this.departureDateTime = departureDateTime;
        this.departureAirportIATA = departureAirportIATA;
        this.departureAirportName = departureAirportName;
        this.departureAirportTerminal = departureAirportTerminal;
    }

    @JsonProperty
    public String getAirline() {
        return airline;
    }

    @JsonProperty
    public String getFlightNumber() {
        return flightNumber;
    }

    @JsonProperty
    public String getFlightLength() {
        return flightLength;
    }

    @JsonProperty
    public String getArrivalDateTime() {
        return arrivalDateTime;
    }

    @JsonProperty
    public String getArrivalAirportIATA() {
        return arrivalAirportIATA;
    }

    @JsonProperty
    public String getArrivalAirportName() {
        return arrivalAirportName;
    }

    @JsonProperty
    public String getArrivalAirportTerminal() {
        return arrivalAirportTerminal;
    }

    @JsonProperty
    public String getDepartureDateTime() {
        return departureDateTime;
    }

    @JsonProperty
    public String getDepartureAirportIATA() {
        return departureAirportIATA;
    }

    @JsonProperty
    public String getDepartureAirportName() {
        return departureAirportName;
    }

    @JsonProperty
    public String getDepartureAirportTerminal() {
        return departureAirportTerminal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightSegment that = (FlightSegment) o;
        return Objects.equals(getAirline(), that.getAirline()) &&
                Objects.equals(getFlightNumber(), that.getFlightNumber()) &&
                Objects.equals(getFlightLength(), that.getFlightLength()) &&
                Objects.equals(getArrivalDateTime(), that.getArrivalDateTime()) &&
                Objects.equals(getArrivalAirportIATA(), that.getArrivalAirportIATA()) &&
                Objects.equals(getArrivalAirportName(), that.getArrivalAirportName()) &&
                Objects.equals(getArrivalAirportTerminal(), that.getArrivalAirportTerminal()) &&
                Objects.equals(getDepartureDateTime(), that.getDepartureDateTime()) &&
                Objects.equals(getDepartureAirportIATA(), that.getDepartureAirportIATA()) &&
                Objects.equals(getDepartureAirportName(), that.getDepartureAirportName()) &&
                Objects.equals(getDepartureAirportTerminal(), that.getDepartureAirportTerminal());
    }
}
