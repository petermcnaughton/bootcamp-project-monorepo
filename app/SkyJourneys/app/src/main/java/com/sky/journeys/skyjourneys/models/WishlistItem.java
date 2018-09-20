package com.sky.journeys.skyjourneys.models;

public class WishlistItem {
    private int id;
    private String location;
    private String image;

    public WishlistItem(int id, String location, String image) {
        this.id = id;
        this.location = location;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
