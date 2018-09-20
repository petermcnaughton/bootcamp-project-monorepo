package com.avocados.skyjourneys.resources;

import com.avocados.skyjourneys.api.FlightDetails;
import com.avocados.skyjourneys.api.FlightLeg;
import com.avocados.skyjourneys.api.FlightSegment;
import com.avocados.skyjourneys.api.FlightTicket;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/details/flight/{bookingId}")
@Produces(MediaType.APPLICATION_JSON)
public class FlightDetailsResource {

    public FlightDetailsResource(){
        //Empty constructor
    }

    @GET
    public FlightDetails get(@PathParam("bookingId") LongParam bookingId){
        //throw new RuntimeException();

        //Make request to API/DB...

        /*
         * MOCK DATA
         */

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

        return new FlightDetails(
                "722341_0_12",
                outLeg,
                retLeg,
                2122.00,
                "GBP",
                new FlightTicket[]{tkt});

        //Compile data into:
        //- Flight Ticket
        //- Flight Segment(s)
        //- Flight Leg(s)
        //- Flight Details

        //Return result
    }

}
