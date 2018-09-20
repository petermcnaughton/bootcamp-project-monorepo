package com.sky.journeys.skyjourneys.pages.pastjourneys;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sky.journeys.skyjourneys.R;
import com.sky.journeys.skyjourneys.controllers.APIQuery;
import com.sky.journeys.skyjourneys.controllers.JourneyResponseHandler;
import com.sky.journeys.skyjourneys.recyclerView.RecyclerViewManager;
import com.sky.journeys.skyjourneys.utilities.NetworkUtils;

import java.net.URL;

public class PastJourneysListActivity extends AppCompatActivity {
    private static final String queryURL = "https://firebasestorage.googleapis.com/v0/b/journeys-6208e.appspot.com/o/trips.json?alt=media&token=dbcb84d9-ef11-44f4-8302-90b015a8800d";
    private final Context context = this;
    private final Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_journeys_list);

        URL builtQueryURL = NetworkUtils.buildUrl(queryURL);
        String queryResponse = "";

        try {
            queryResponse = new APIQuery().execute(builtQueryURL).get();
        } catch (Exception e) {
            e.getStackTrace();
        }

        RecyclerViewManager manager = new RecyclerViewManager(activity, context);
        RecyclerView recyclerView = manager.createRecyclerView(new LinearLayoutManager(context));
        manager.setJourneyAdapter(recyclerView, new JourneyResponseHandler(queryResponse, activity, context));
    }
}
