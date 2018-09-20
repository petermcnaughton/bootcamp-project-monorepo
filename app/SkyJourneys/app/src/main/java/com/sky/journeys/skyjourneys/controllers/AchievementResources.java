package com.sky.journeys.skyjourneys.controllers;

import com.sky.journeys.skyjourneys.R;

public class AchievementResources {
    private int color;

    public AchievementResources(int id) {
        switch (id % 6) {
            case 1:
                this.color = R.color.colorMediumPink;
                break;
            case 2:
                this.color = R.color.colorOrange;
                break;
            case 3:
                this.color = R.color.colorLightBlue;
                break;
            case 4:
                this.color = R.color.colorDustPink;
                break;
            case 5:
                this.color = R.color.colorDarkOrange;
                break;
            case 0:
                this.color = R.color.colorLightPurple;
                break;

            default:
                this.color = R.color.colorLightBlue;
        }
    }

    public int getColor() {
        return color;
    }
}
