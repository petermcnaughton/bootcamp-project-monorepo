package com.sky.journeys.skyjourneys.controllers;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class IntentDataHandler {
    private Activity activity;

    public IntentDataHandler(Activity activity) {
        this.activity = activity;
    }

    public String getIntentData(String name) {
        return activity.getIntent().getStringExtra(name);
    }

    public int getIntentIntData(String name) {
        return activity.getIntent().getIntExtra(name, 0);
    }

    public void setTextOfTextView(TextView textView, String text) {
        textView.setText(text);
    }

    public void setImageOfImageView(ImageView imageView, String imagePath) {
        Picasso.get().load(imagePath).resize(2000, 1000)
                .centerCrop().into(imageView);
    }

    public void setIconImage(ImageView imageView, String imagePath) {
        Picasso.get().load(imagePath).resize(400, 400).centerInside().into(imageView);
    }
}
