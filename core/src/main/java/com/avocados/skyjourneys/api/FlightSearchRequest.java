package com.avocados.skyjourneys.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Objects;

public class FlightSearchRequest {

    private String sessionId;
    private String destinationCity;
    private String departureCity;
    private String departureDate;
    private String returnDate;


    private FlightSearchRequestPerson[] persons;

    private boolean sortByPrice;
    private boolean sortHeightestFirst;
    private boolean directFlightsOnly;

    @JsonCreator
    public FlightSearchRequest(String sessionId,
                               String destinationCity,
                               String departureCity,
                               String departureDate,
                               String returnDate,
                               FlightSearchRequestPerson[] persons,
                               boolean sortByPrice,
                               boolean sortHighestFirst,
                               boolean directFlightsOnly) {
        this.sessionId = sessionId;
        this.destinationCity = destinationCity;
        this.departureCity = departureCity;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.persons = persons;
        this.sortByPrice = sortByPrice;
        this.sortHeightestFirst = sortHighestFirst;
        this.directFlightsOnly = directFlightsOnly;
    }

    @JsonProperty
    public String getSessionId() {
        return sessionId;
    }

    @JsonProperty
    public String getDestinationCity() {
        return destinationCity;
    }

    @JsonProperty
    public String getDepartureCity() {
        return departureCity;
    }

    @JsonProperty
    public String getDepartureDate() {
        return departureDate;
    }

    @JsonProperty
    public String getReturnDate() {
        return returnDate;
    }

    @JsonProperty
    public FlightSearchRequestPerson[] getPersons() {
        return persons;
    }

    @JsonProperty
    public boolean isSortByPrice() {
        return sortByPrice;
    }

    @JsonProperty
    public boolean isSortHeightestFirst() {
        return sortHeightestFirst;
    }

    @JsonProperty
    public boolean isDirectFlightsOnly() {
        return directFlightsOnly;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightSearchRequest request = (FlightSearchRequest) o;
        return isSortByPrice() == request.isSortByPrice() &&
                isSortHeightestFirst() == request.isSortHeightestFirst() &&
                isDirectFlightsOnly() == request.isDirectFlightsOnly() &&
                Objects.equals(getSessionId(), request.getSessionId()) &&
                Objects.equals(getDestinationCity(), request.getDestinationCity()) &&
                Objects.equals(getDepartureCity(), request.getDepartureCity()) &&
                Objects.equals(getDepartureDate(), request.getDepartureDate()) &&
                Objects.equals(getReturnDate(), request.getReturnDate()) &&
                Arrays.equals(getPersons(), request.getPersons());
    }
}

class FlightSearchRequestPerson {

    private String passengerType;
    private int quantity;

    @JsonCreator
    public FlightSearchRequestPerson(String type, int quantity) {
        this.passengerType = type;
        this.quantity = quantity;
    }

    @JsonProperty
    public String getPassengerType() {
        return passengerType;
    }

    @JsonProperty
    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightSearchRequestPerson that = (FlightSearchRequestPerson) o;
        return getQuantity() == that.getQuantity() &&
                Objects.equals(getPassengerType(), that.getPassengerType());
    }
}