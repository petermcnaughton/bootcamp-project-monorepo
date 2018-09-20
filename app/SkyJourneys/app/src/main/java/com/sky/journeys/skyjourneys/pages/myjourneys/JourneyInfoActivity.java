package com.sky.journeys.skyjourneys.pages.myjourneys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sky.journeys.skyjourneys.R;
import com.sky.journeys.skyjourneys.controllers.IntentDataHandler;

public class JourneyInfoActivity extends AppCompatActivity {

    private ImageView backgroundImageView;
    private TextView locationTextView;
    private TextView datesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey_info);
        setViews();

        IntentDataHandler handler = new IntentDataHandler(this);
        handler.setTextOfTextView(locationTextView, handler.getIntentData("location"));
        handler.setTextOfTextView(datesTextView, handler.getIntentData("dates"));
        handler.setImageOfImageView(backgroundImageView, handler.getIntentData("image"));
    }

    public void setViews() {
        locationTextView = (TextView) findViewById(R.id.journey_location);
        datesTextView = (TextView) findViewById(R.id.journey_dates);
        backgroundImageView = (ImageView) findViewById(R.id.background);
    }

    public void flightInfo(View view){
        startActivity(new Intent(JourneyInfoActivity.this, Flight.class));
    }

    public void accommodationInfo(View view){
        startActivity(new Intent(JourneyInfoActivity.this, AccommodationPage.class));
    }
    public void activityInfo(View view){
        startActivity(new Intent(JourneyInfoActivity.this, ActivitiesList.class));
    }

}
