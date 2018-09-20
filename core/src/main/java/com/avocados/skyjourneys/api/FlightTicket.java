package com.avocados.skyjourneys.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Objects;

public class FlightTicket {
    private final String type;
    private final int quantity;
    private final double fare;
    private final double tax;
    private final String currency;
    private final String[] ticketDetails;

    @JsonCreator
    public FlightTicket(String type, int quantity, double fare, double tax, String currency, String[] ticketDetails) {
        this.type = type;
        this.quantity = quantity;
        this.fare = fare;
        this.tax = tax;
        this.currency = currency;
        this.ticketDetails = ticketDetails;
    }

    @JsonProperty
    public String getType() {
        return type;
    }

    @JsonProperty
    public int getQuantity() {
        return quantity;
    }

    @JsonProperty
    public double getFare() {
        return fare;
    }

    @JsonProperty
    public double getTax() {
        return tax;
    }

    @JsonProperty
    public String getCurrency() {
        return currency;
    }

    @JsonProperty
    public String[] getTicketDetails() {
        return ticketDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightTicket that = (FlightTicket) o;
        return getQuantity() == that.getQuantity() &&
                Double.compare(that.getFare(), getFare()) == 0 &&
                Double.compare(that.getTax(), getTax()) == 0 &&
                Objects.equals(getType(), that.getType()) &&
                Objects.equals(getCurrency(), that.getCurrency()) &&
                Arrays.equals(getTicketDetails(), that.getTicketDetails());
    }
}
