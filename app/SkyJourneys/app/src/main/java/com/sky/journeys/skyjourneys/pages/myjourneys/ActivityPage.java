package com.sky.journeys.skyjourneys.pages.myjourneys;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sky.journeys.skyjourneys.R;
import com.squareup.picasso.Picasso;

public class ActivityPage extends AppCompatActivity {
    private TextView title;
    private ImageView image;
    private TextView Date;
    private TextView numberOfPeople;
    private TextView Duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        Intent intent = getIntent();
        title = (TextView) findViewById(R.id.title);
        image = (ImageView) findViewById(R.id.image);
        title.setText(intent.getStringExtra("title"));

        Picasso.get().load(intent.getStringExtra("image"))
                .resize(200, 153)
                .into(image);


    }



}
