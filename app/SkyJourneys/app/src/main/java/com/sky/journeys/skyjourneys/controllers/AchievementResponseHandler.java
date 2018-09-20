package com.sky.journeys.skyjourneys.controllers;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sky.journeys.skyjourneys.R;
import com.sky.journeys.skyjourneys.interfaces.APIResponseHandler;
import com.sky.journeys.skyjourneys.models.Achievement;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AchievementResponseHandler implements APIResponseHandler {
    private String queryResponse;
    private List<Achievement> dataset;
    private Activity activity;
    private Context context;

    public AchievementResponseHandler(String queryResponse, Activity activity, Context context) {
        this.queryResponse = queryResponse;
        this.activity = activity;
        this.context = context;

        handleAPIResponse(queryResponse);

        if (dataset.size() == 0) {
            removeRecyclerView();
        } else {
            removeDefaults();
        }
    }

    public void removeRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.recycler_view);
        recyclerView.setVisibility(View.GONE);
    }

    public void removeDefaults() {
        activity.findViewById(R.id.default_background).setVisibility(View.GONE);
        activity.findViewById(R.id.default_text_1).setVisibility(View.GONE);
        activity.findViewById(R.id.default_text_2).setVisibility(View.GONE);
    }

    @Override
    public void handleAPIResponse(String APIResponse) {
        ArrayList<Achievement> achievements = new ArrayList<>();

        if (APIResponse != null && !APIResponse.equals("")) {
            try {
                JSONObject jsonObject = new JSONObject(APIResponse);
                JSONArray achievementsArray = jsonObject.getJSONArray("achievements");

                for (int i = 0; i < achievementsArray.length(); i++) {
                    JSONObject currentAchievement = achievementsArray.getJSONObject(i);

                    int id = currentAchievement.getInt("id");
                    String name = currentAchievement.getString("name");
                    String description = currentAchievement.getString("description");
                    String imagePath = currentAchievement.getString("image");
                    String prize = currentAchievement.getString("prize");
                    String prizeImagePath = currentAchievement.getString("prize_image");
                    boolean unlocked = currentAchievement.getBoolean("unlocked");

                    achievements.add(new Achievement(id, name, description, imagePath, prize, prizeImagePath, unlocked));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.dataset = achievements;
    }

    public List<Achievement> getDataset() {
        return dataset;
    }
}
