package com.sky.journeys.skyjourneys.pages.profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;

import com.sky.journeys.skyjourneys.R;
import com.sky.journeys.skyjourneys.controllers.APIQuery;
import com.sky.journeys.skyjourneys.controllers.ProfileResponseHandler;
import com.sky.journeys.skyjourneys.models.Customer;
import com.sky.journeys.skyjourneys.pages.achievements.AchievementsActivity;
import com.sky.journeys.skyjourneys.pages.myjourneys.CurrentBooking;
import com.sky.journeys.skyjourneys.pages.pastjourneys.PastJourneysListActivity;
import com.sky.journeys.skyjourneys.pages.wishlist.Wishlist;
import com.sky.journeys.skyjourneys.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.net.URL;

public class ProfileActivity extends AppCompatActivity {
    private static final String queryURL = "https://firebasestorage.googleapis.com/v0/b/journeys-6208e.appspot.com/o/profile.json?alt=media&token=3359e300-1d22-448e-a044-09161200a515";
    private final Context context = this;
    private final Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        URL builtQueryURL = NetworkUtils.buildUrl(queryURL);
        String queryResponse = "";

        try {
            queryResponse = new APIQuery().execute(builtQueryURL).get();
        } catch (Exception e) {
            e.getStackTrace();
        }

        Customer customer = new ProfileResponseHandler(queryResponse, activity, context).getCustomer();

        ImageView profilePic = (ImageView) findViewById(R.id.profile_image);
        Picasso.get().load(customer.getProfileImage()).into(profilePic);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profile_menu, menu);
        return true;
    }

    public void startMyBookingsActivity(View view) {
        Intent intent = new Intent(context, CurrentBooking.class);
        startActivity(intent);
    }

    public void startPastBookingsActivity(View view) {
        Intent intent = new Intent(context, PastJourneysListActivity.class);
        startActivity(intent);
    }

    public void startWishlistActivity(View view) {
        Intent intent = new Intent(context, Wishlist.class);
        startActivity(intent);
    }

    public void startAchievementsActivity(View view) {
        Intent intent = new Intent(context, AchievementsActivity.class);
        startActivity(intent);
    }

}
