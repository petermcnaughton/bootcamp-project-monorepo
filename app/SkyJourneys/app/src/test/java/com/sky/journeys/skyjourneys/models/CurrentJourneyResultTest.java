package com.sky.journeys.skyjourneys.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CurrentJourneyResultTest {
    private CurrentJourneyResult journey;
    @Before
    public void setUp() {
        this.journey = new CurrentJourneyResult("1", "London", "Valencia", "01/01/2018", "02/01/2018", "http://www.example.com");
    }

    @Test
    public void getIdReturnsValidIdString() {
        assertEquals("1", journey.getId());
    }

    @Test
    public void getFromReturnsValidFromString() {
        assertEquals("London", journey.getFrom());
    }

    @Test
    public void getToReturnsValidToString() {
        assertEquals("Valencia", journey.getTo());
    }

    @Test
    public void getInboundDateReturnsValidInboundDateString() {
        assertEquals("01/01/2018", journey.getOutboundDate());
    }

    @Test
    public void getOutboundDateReturnsValidOutboundDateString() {
        assertEquals("02/01/2018", journey.getInboundDate());
    }

    @Test
    public void getImageReturnsValidImagePathString() {
        assertEquals("http://www.example.com", journey.getImage());
    }
}