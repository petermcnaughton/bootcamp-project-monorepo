package com.sky.journeys.skyjourneys.models;

public class Achievement {
    private int id;
    private String name;
    private String description;
    private String imagePath;
    private String prize;
    private String prizeImagePath;
    private boolean unlocked;

    public Achievement(int id, String name, String description, String imagePath, String prize, String prizeImagePath, boolean unlocked) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.prize = prize;
        this.prizeImagePath = prizeImagePath;
        this.unlocked = unlocked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getPrizeImagePath() {
        return this.prizeImagePath;
    }

    public void setPrizeImagePath(String prizeImagePath) {
        this.prizeImagePath = prizeImagePath;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }
}
