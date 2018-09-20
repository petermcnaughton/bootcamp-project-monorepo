package com.avocados.skyjourneys.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

public class FlightSearchResponseTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializeFlightSearchResponceToJSON() throws Exception {

        FlightSearchResponse resp;

        FlightTicket tkt1 = new FlightTicket(
                "ADT",
                3,
                300.00,
                128.05,
                "GBP",
                new String[]{"--NO RESTRICTIONS--"}
        );

        FlightSegment out1a = new FlightSegment(
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

        FlightSegment ret1a = new FlightSegment(
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

        FlightLeg outLeg1 = new FlightLeg(new FlightSegment[]{out1a});
        FlightLeg retLeg1 = new FlightLeg(new FlightSegment[]{ret1a});

        FlightDetails fd1 = new FlightDetails(
                "722341_0_12",
                outLeg1,
                retLeg1,
                2122.00,
                "GBP",
                new FlightTicket[]{tkt1}
        );
        ////////////


        FlightTicket tkt2 = new FlightTicket(
                "ADT",
                3,
                420.00,
                150.05,
                "GBP",
                new String[]{"TICKETS ARE NON REFUNDABLE AFTER DEPARTURE|LAST TKT DTE|21JUL18| - SEE ADV PURCHASE|"}
        );

        FlightSegment out2a = new FlightSegment(
                "British Airways",
                "2273",
                "0800",
                "2018-09-16T19:35:00",
                "JFK",
                "John F Kennedy Intl",
                "7",
                "2018-09-16T16:35:00",
                "LGW",
                "London Gatwick",
                "S"
        );

        FlightSegment ret2a = new FlightSegment(
                "British Airways",
                "178",
                "0650",
                "2018-09-21T19:45:00",
                "LHR",
                "London Heathrow",
                "5",
                "2018-09-21T07:55:00",
                "JFK",
                "John F Kennedy Intl",
                "7"
        );

        FlightLeg outLeg2 = new FlightLeg(new FlightSegment[]{out2a});
        FlightLeg retLeg2 = new FlightLeg(new FlightSegment[]{ret2a});

        FlightDetails fd2 = new FlightDetails(
                "723341_0_12",
                outLeg2,
                retLeg2,
                2418.00,
                "GBP",
                new FlightTicket[]{tkt2}
        );

        resp = new FlightSearchResponse(new FlightDetails[]{fd1, fd2}, true);


        final String exp = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/flightsearchresponse.json"), FlightSearchResponse.class)
        );

        assertThat(MAPPER.writeValueAsString(resp)).isEqualTo(exp);

    }


    @Test
    public void testSerializeFlightSearchResultsWithConsistentOrder() throws Exception {

        FlightSearchResponse resp;

        FlightTicket tkt2 = new FlightTicket(
                "ADT",
                3,
                300.00,
                128.05,
                "GBP",
                new String[]{"--NO RESTRICTIONS--"}
        );

        FlightSegment out2a = new FlightSegment(
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

        FlightSegment ret2a = new FlightSegment(
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

        FlightLeg outLeg2 = new FlightLeg(new FlightSegment[]{out2a});
        FlightLeg retLeg2 = new FlightLeg(new FlightSegment[]{ret2a});

        FlightDetails fd2 = new FlightDetails(
                "722341_0_12",
                outLeg2,
                retLeg2,
                2122.00,
                "GBP",
                new FlightTicket[]{tkt2}
        );
        ////////////


        FlightTicket tkt1 = new FlightTicket(
                "ADT",
                3,
                420.00,
                150.05,
                "GBP",
                new String[]{"TICKETS ARE NON REFUNDABLE AFTER DEPARTURE|LAST TKT DTE|21JUL18| - SEE ADV PURCHASE|"}
        );

        FlightSegment out1a = new FlightSegment(
                "British Airways",
                "2273",
                "0800",
                "2018-09-16T19:35:00",
                "JFK",
                "John F Kennedy Intl",
                "7",
                "2018-09-16T16:35:00",
                "LGW",
                "London Gatwick",
                "S"
        );

        FlightSegment ret1a = new FlightSegment(
                "British Airways",
                "178",
                "0650",
                "2018-09-21T19:45:00",
                "LHR",
                "London Heathrow",
                "5",
                "2018-09-21T07:55:00",
                "JFK",
                "John F Kennedy Intl",
                "7"
        );

        FlightLeg outLeg1 = new FlightLeg(new FlightSegment[]{out1a});
        FlightLeg retLeg1 = new FlightLeg(new FlightSegment[]{ret1a});

        FlightDetails fd1 = new FlightDetails(
                "723341_0_12",
                outLeg1,
                retLeg1,
                2418.00,
                "GBP",
                new FlightTicket[]{tkt1}
        );

        resp = new FlightSearchResponse(new FlightDetails[]{fd1, fd2}, true);


        final String exp = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/flightsearchresponse.json"), FlightSearchResponse.class)
        );

        assertThat(MAPPER.writeValueAsString(resp)).isNotEqualToIgnoringWhitespace(exp);

    }



}
