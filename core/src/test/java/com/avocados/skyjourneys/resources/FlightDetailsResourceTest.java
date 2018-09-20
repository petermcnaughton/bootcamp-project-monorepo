package com.avocados.skyjourneys.resources;

import com.avocados.skyjourneys.api.FlightDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.testing.junit.ResourceTestRule;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

import java.io.IOException;

public class FlightDetailsResourceTest {

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new FlightDetailsResource())
            .build();

    private final FlightDetails flightDetails;

    {
        try {
            flightDetails = Jackson.newObjectMapper().readValue(fixture("fixtures/flightdetails.json"), FlightDetails.class);
        } catch (IOException e) {
            throw new RuntimeException("Could not instantiate fixtures");
        }
    }

    @Test
    public void testThatDataReturnedFromFlightDetailsAPIMatchesFixture(){
        assertThat(
                resources.target("/api/details/flight/12345").request().get(FlightDetails.class)
        ).isEqualTo(flightDetails);
    }

}
