package com.avocados.skyjourneys.api;

import static io.dropwizard.testing.FixtureHelpers.*;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FlightDetailsTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializeFlightDetailsToJSON() throws Exception {
        FlightTicket tkt = new FlightTicket(
                "ADT",
                3,
                300.00,
                128.05,
                "GBP",
                new String[]{"--NO RESTRICTIONS--"}
        );

        FlightSegment out1 = new FlightSegment(
                "American Airways",
                "344",
                "0810",
                "2018-09-16T20:40:00",
                "JFK",
                "John F Kennedy Intl",
                "4",
                "2018-09-16T17:30:00",
                "LHR",
                "London Heathrow",
                "5"
        );

        FlightSegment ret1 = new FlightSegment(
                "American Airways",
                "712",
                "0705",
                "2018-09-22T10:50:00",
                "LHR",
                "London Heathrow",
                "5",
                "2018-09-21T22:45:00",
                "JFK",
                "John F Kennedy Intl",
                "4"
        );

        FlightLeg outLeg = new FlightLeg(new FlightSegment[]{out1});
        FlightLeg retLeg = new FlightLeg(new FlightSegment[]{ret1});

        FlightDetails fd = new FlightDetails(
                "722341_0_12",
                outLeg,
                retLeg,
                2122.00,
                "GBP",
                new FlightTicket[]{tkt}
        );

        final String exp = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/flightdetails.json"), FlightDetails.class)
        );

        assertThat(MAPPER.writeValueAsString(fd)).isEqualTo(exp);

    }

}
