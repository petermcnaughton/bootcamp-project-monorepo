package com.sky.journeys.skyjourneys.models;

public class Customer {
    private int id;
    private String profileImage;

    public Customer(int id, String profileImage) {
        this.id = id;
        this.profileImage = profileImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
