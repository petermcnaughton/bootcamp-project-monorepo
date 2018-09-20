package com.sky.journeys.skyjourneys.recyclerView;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.sky.journeys.skyjourneys.R;
import com.sky.journeys.skyjourneys.controllers.AchievementResponseHandler;
import com.sky.journeys.skyjourneys.controllers.JourneyResponseHandler;

public class RecyclerViewManager {
    private Activity activity;
    private Context context;

    public RecyclerViewManager(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    public RecyclerView createRecyclerView(RecyclerView.LayoutManager layoutManager) {
        RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        return recyclerView;
    }

    public void setJourneyAdapter(RecyclerView recyclerView, JourneyResponseHandler handler) {
        JourneyAdapter adapter = new JourneyAdapter(context, handler.getDataset());
        recyclerView.setAdapter(adapter);
    }

    public void setAchievementAdapter(RecyclerView recyclerView, AchievementResponseHandler handler) {
        AchievementAdapter adapter = new AchievementAdapter(context, handler.getDataset());
        recyclerView.setAdapter(adapter);
    }
}
