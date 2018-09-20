package com.sky.journeys.skyjourneys.pages.myjourneys;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sky.journeys.skyjourneys.R;
import com.sky.journeys.skyjourneys.controllers.APIQuery;
import com.sky.journeys.skyjourneys.interfaces.APIResponseHandler;
import com.sky.journeys.skyjourneys.models.ActivitiesListResult;
import com.sky.journeys.skyjourneys.recyclerView.ActivitiesListAdapter;
import com.sky.journeys.skyjourneys.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ActivitiesList extends AppCompatActivity implements APIResponseHandler {
    private static final String queryURL = "https://firebasestorage.googleapis.com/v0/b/journeys-6208e.appspot.com/o/Activities.json?alt=media&token=d101d78b-cadb-4353-9b87-50f368abe0a1";
    private Context context = this;
    private List<ActivitiesListResult> activitiesListResults = new ArrayList<>();
    private RecyclerView recyclerView;
    private ActivitiesListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_journeys_list);

        URL builtQueryURL = NetworkUtils.buildUrl(queryURL);
        queryAPI(builtQueryURL);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ActivitiesListAdapter(this, activitiesListResults, Picasso.get());
        recyclerView.setAdapter(adapter);
    }

    // Move logic to another controller class
    public void queryAPI(URL url) {
        APIQuery query = new APIQuery();
        AsyncTask task = query.execute(url);

        try {
            String temp = (String) task.get();
            handleAPIResponse(temp);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

//    public void startJourneyInfoActivity(View view) {
//        Intent intent = new Intent(context, JourneyInfoActivity.class);
//        //TODO: get dynamic location
//        intent.putExtra("location", "");
//        startActivity(intent);
//    }

    // This is the method that handles the response, called by the APIQuery
    @Override
    public void handleAPIResponse(String APIResponse) {
        ArrayList<ActivitiesListResult> activitiesList = new ArrayList<>();

        if (APIResponse != null && !APIResponse.equals("")) {
            try {
                //parse json
                JSONObject jsonObject = new JSONObject(APIResponse);
                JSONArray activities = jsonObject.getJSONArray("activities");

                //extract fields
                for (int i = 0; i < activities.length(); i++) {
                    JSONObject currentActivity = activities.getJSONObject(i);
                    String id = currentActivity.getString("id");
                    String title = currentActivity.getString("title");
                    String date = currentActivity.getString("date");
                    String city = currentActivity.getString("city");
                    String imagePath = currentActivity.getString("image");
                    ActivitiesListResult activity = new ActivitiesListResult(id, title, date, city, imagePath);
                    activitiesList.add(activity);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.activitiesListResults = activitiesList;
    }


}
