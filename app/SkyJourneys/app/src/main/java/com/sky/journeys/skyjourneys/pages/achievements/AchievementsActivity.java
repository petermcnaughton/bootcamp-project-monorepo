package com.sky.journeys.skyjourneys.pages.achievements;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sky.journeys.skyjourneys.R;
import com.sky.journeys.skyjourneys.controllers.APIQuery;
import com.sky.journeys.skyjourneys.controllers.AchievementResponseHandler;
import com.sky.journeys.skyjourneys.recyclerView.RecyclerViewManager;
import com.sky.journeys.skyjourneys.utilities.NetworkUtils;

import java.net.URL;

public class AchievementsActivity extends AppCompatActivity {
    private static final String queryURL = "https://firebasestorage.googleapis.com/v0/b/journeys-6208e.appspot.com/o/achievements.json?alt=media&token=98db2fd9-95de-410d-ab31-99b3efcaebce";
    private final Context context = this;
    private final Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);

        URL builtQueryURL = NetworkUtils.buildUrl(queryURL);
        String queryResponse = "";

        try {
            queryResponse = new APIQuery().execute(builtQueryURL).get();
        } catch (Exception e) {
            e.getStackTrace();
        }

        RecyclerViewManager manager = new RecyclerViewManager(activity, context);
        RecyclerView recyclerView = manager.createRecyclerView(new GridLayoutManager(context, 2));
        manager.setAchievementAdapter(recyclerView, new AchievementResponseHandler(queryResponse, activity, context));
    }
}
