package com.sky.journeys.skyjourneys.models;

public class ActivitiesListResult {
    private String id;
    private String title;
    private String date;
    private String city;
    private String image;


    public ActivitiesListResult(String id, String title, String date, String city, String image) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.city = city;
        this.image = image;
    }
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getCity() {
        return city;
    }

    public String getImage() {
        return image;
    }


}
