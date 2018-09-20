package com.sky.journeys.skyjourneys.controllers;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.View;

import com.sky.journeys.skyjourneys.R;
import com.sky.journeys.skyjourneys.interfaces.APIResponseHandler;
import com.sky.journeys.skyjourneys.models.CurrentJourneyResult;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class JourneyResponseHandler implements APIResponseHandler {
    private String queryResponse;
    private List<CurrentJourneyResult> dataset;
    private Activity activity;
    private Context context;
    private JourneyType type;
    private enum JourneyType {
        UPCOMING,
        PAST
    }

    public JourneyResponseHandler(String queryResponse, Activity activity, Context context) {
        this.queryResponse = queryResponse;
        this.activity = activity;
        this.context = context;
        this.type = context.getClass().getSimpleName().equals("CurrentBooking") ? JourneyType.UPCOMING : JourneyType.PAST;

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
        ArrayList<CurrentJourneyResult> journeys = new ArrayList<>();

        if (APIResponse != null && !APIResponse.equals("")) {
            try {
                JSONObject jsonObject = new JSONObject(APIResponse);
                JSONArray trips = jsonObject.getJSONArray("trips");

                for (int i = 0; i < trips.length(); i++) {
                    JSONObject currentTrip = trips.getJSONObject(i);

                    String id = currentTrip.getString("id");
                    String fromLocation = currentTrip.getString("from");
                    String toLocation = currentTrip.getString("to");
                    String outboundDate = currentTrip.getString("outbound-date");
                    String inboundDate = currentTrip.getString("inbound-date");
                    String imagePath = currentTrip.getString("image");

                    if (type == JourneyType.UPCOMING && isFuture(outboundDate)) {
                        CurrentJourneyResult journey = new CurrentJourneyResult(id, fromLocation, toLocation, outboundDate, inboundDate, imagePath);
                        journeys.add(journey);
                    }

                    if (type == JourneyType.PAST && !isFuture(outboundDate)) {
                        CurrentJourneyResult journey = new CurrentJourneyResult(id, fromLocation, toLocation, outboundDate, inboundDate, imagePath);
                        journeys.add(journey);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.dataset = journeys;
    }

    private boolean isFuture(String date) {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        try {
            Date parsedDate = format.parse(date);
            if (new Date().before(parsedDate) || DateUtils.isToday(parsedDate.getTime())) {
                return true;
            }
        } catch (ParseException e) {
            e.getStackTrace();
        }

        return false;
    }

    public List<CurrentJourneyResult> getDataset() {
        return dataset;
    }
}
