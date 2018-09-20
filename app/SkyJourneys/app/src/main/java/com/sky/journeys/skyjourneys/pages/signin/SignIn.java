package com.sky.journeys.skyjourneys.pages.signin;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.sky.journeys.skyjourneys.pages.profile.ProfileActivity;

import com.sky.journeys.skyjourneys.R;


public class SignIn extends AppCompatActivity{

    private Button profileButton;
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        profileButton = (Button) findViewById(R.id.signin);
    }


    public void startProfile(View view) {
        Intent intent = new Intent(context, ProfileActivity.class);
        startActivity(intent);
    }
}
