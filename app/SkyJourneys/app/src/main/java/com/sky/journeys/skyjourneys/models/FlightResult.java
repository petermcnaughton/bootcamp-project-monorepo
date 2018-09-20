package com.sky.journeys.skyjourneys.models;

import java.util.StringTokenizer;

public class FlightResult {


    private String fromAirport;
    private String toAirport;
    private String fromCity;
    private String toCity;
    private int flightNumber;
    private int seatNumber;
    private int gateNumber;
    private String departsTime;

    public FlightResult(String fromAirport , String toAirpot, String fromCity, String toCity, int flightNumber,
                        int seatNumber, int gateNumber, String departsTime){

        this.fromAirport = fromAirport;
        this.toAirport = toAirpot;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.flightNumber = flightNumber;
        this.seatNumber = seatNumber;
        this.gateNumber = gateNumber;
        this.departsTime = departsTime;




    }



}
