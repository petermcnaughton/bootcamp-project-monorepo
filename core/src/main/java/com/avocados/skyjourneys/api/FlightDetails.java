package com.avocados.skyjourneys.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Objects;


public class FlightDetails {
    private final String bookingId;
    private final FlightLeg outbound;
    private final FlightLeg returnbound;

    private final double price;
    private final String currency;

    private final FlightTicket[] tickets;

    @JsonCreator
    public FlightDetails(String bookingId, FlightLeg outbound, FlightLeg returnbound, double price, String currency, FlightTicket[] tickets) {
        this.bookingId = bookingId;
        this.outbound = outbound;
        this.returnbound = returnbound;
        this.price = price;
        this.currency = currency;
        this.tickets = tickets;
    }

    @JsonProperty
    public String getBookingId() {
        return bookingId;
    }

    @JsonProperty
    public FlightLeg getOutbound() {
        return outbound;
    }

    @JsonProperty
    public FlightLeg getReturnbound() {
        return returnbound;
    }

    @JsonProperty
    public double getPrice() {
        return price;
    }

    @JsonProperty
    public String getCurrency() {
        return currency;
    }

    @JsonProperty
    public FlightTicket[] getTickets() {
        return tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightDetails that = (FlightDetails) o;
        return Double.compare(that.getPrice(), getPrice()) == 0 &&
                Objects.equals(getBookingId(), that.getBookingId()) &&
                Objects.equals(getOutbound(), that.getOutbound()) &&
                Objects.equals(getReturnbound(), that.getReturnbound()) &&
                Objects.equals(getCurrency(), that.getCurrency()) &&
                Arrays.equals(getTickets(), that.getTickets());
    }
}
