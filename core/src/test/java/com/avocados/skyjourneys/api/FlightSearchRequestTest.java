package com.avocados.skyjourneys.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

public class FlightSearchRequestTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void tellSearchRequestSerialisationsFromAndToJAENibCorrect() throws Exception{
        FlightSearchRequest request = new FlightSearchRequest(
                "an201mn-nf93n20ufh1wss",
                "NYC",
                "LON",
                "2018-09-16T00:00:00",
                "2018-09-21T00:00:00",
                new FlightSearchRequestPerson[]{
                        new FlightSearchRequestPerson("ADT", 3)
                },
                true,
                false,
                true
        );

        final String exp = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/flightsearchrequest.json"), FlightSearchRequest.class)
        );

        assertThat(MAPPER.writeValueAsString(request)).isEqualTo(exp);

    }

}

