package com.avocados.skyjourneys.resources;

import com.avocados.skyjourneys.api.FlightSearchRequest;
import com.avocados.skyjourneys.api.FlightSearchResponse;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

public class FlightSearchResourceTest {

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new FlightSearchResource())
            .build();


    private final FlightSearchRequest request;

    {
        try {
            request = Jackson.newObjectMapper().readValue(fixture("fixtures/flightsearchrequest.json"), FlightSearchRequest.class);
        } catch (IOException e) {
            throw new RuntimeException("Could not instantiate request fixture");
        }
    }

    private final FlightSearchResponse response;

    {
        try {
            response = Jackson.newObjectMapper().readValue(fixture("fixtures/flightsearchresponse.json"), FlightSearchResponse.class);
        } catch (IOException e) {
            throw new RuntimeException("Could not instantiate response fixture");
        }
    }

    private final String illegalTypeRequest = "Malformed Data";

    @Test
    public void testThatEndpointReturnsThePlaceholderData(){

        assertThat(resources.target("/api/search/flights")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(request)).readEntity(FlightSearchResponse.class)
        ).isEqualTo(response);
    }

    @Test
    public void testThatEndpointAcceptsThePlaceholderRequest(){
        List<Integer> expectedResponceCodes = Arrays.asList(200, 202);

        assertThat(resources.target("/api/search/flights")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(request)).getStatus()
        ).isIn(expectedResponceCodes);
    }

    @Test
    public void testThatEndpointRejectsBodysOfIncorrectMediaType(){
        assertThat(resources.target("/api/search/flights")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.text(illegalTypeRequest)).getStatus()
        ).isEqualTo(415);
    }

    @Test
    public void testThatEndpointAlertsClientIfJSONIsUnacceptable(){
        assertThat(resources.target("/api/search/flights")
                .request(MediaType.APPLICATION_XML_TYPE)
                .post(Entity.json(request)).getStatus()
        ).isEqualTo(406);
    }
}
