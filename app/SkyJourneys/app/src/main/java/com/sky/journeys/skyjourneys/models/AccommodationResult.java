package com.sky.journeys.skyjourneys.models;

import android.widget.ImageView;
import android.widget.TextView;

public class AccommodationResult {

    private String hotelName;
    private String hotelArea;
    private String roomType;
    private int numberOfPeople;
    private int nights;
    private String hotelAddress;
    private String checkIn;
    private String checkOut;
    private String hotelImage;

    public AccommodationResult (String hotelName, String hotelArea, String roomType, int numberOfPeople, int nights, String hotelAddress, String checkIn, String checkOut, String hotelImage){
        this.hotelName = hotelName;

        this.hotelArea = hotelArea;
        this.roomType = roomType;
        this.numberOfPeople = numberOfPeople;
        this.nights = nights;
        this.hotelAddress = hotelAddress;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.hotelImage = hotelImage;



    }

}
