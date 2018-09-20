package com.sky.journeys.skyjourneys.controllers;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.sky.journeys.skyjourneys.R;
import com.sky.journeys.skyjourneys.interfaces.APIResponseHandler;
import com.sky.journeys.skyjourneys.models.WishlistItem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WishlistResponseHandler implements APIResponseHandler {
    private String queryResponse;
    private List<WishlistItem> dataset;
    private Activity activity;
    private Context context;

    public WishlistResponseHandler(String queryResponse, Activity activity, Context context) {
        this.queryResponse = queryResponse;
        this.activity = activity;
        this.context = context;

        handleAPIResponse(queryResponse);

        if (dataset.size() == 0) {
            removeViewPager();
        } else {
//            removeDefaults();
        }
    }

    public void removeViewPager() {
        ViewPager viewPager = (ViewPager) activity.findViewById(R.id.viewPager);
        viewPager.setVisibility(View.GONE);
    }

    public void removeDefaults() {
//        activity.findViewById(R.id.default_background).setVisibility(View.GONE);
//        activity.findViewById(R.id.default_text_1).setVisibility(View.GONE);
//        activity.findViewById(R.id.default_text_2).setVisibility(View.GONE);
    }

    @Override
    public void handleAPIResponse(String APIResponse) {
        ArrayList<WishlistItem> wishlist = new ArrayList<>();

        if (APIResponse != null && !APIResponse.equals("")) {
            try {
                JSONObject jsonObject = new JSONObject(APIResponse);
                JSONArray wishlistArray = jsonObject.getJSONArray("wishlist");

                for (int i = 0; i < wishlistArray.length(); i++) {
                    JSONObject wishlistObject = wishlistArray.getJSONObject(i);

                    int id = wishlistObject.getInt("id");
                    String location = wishlistObject.getString("location");
                    String imagePath = wishlistObject.getString("image");

                    wishlist.add(new WishlistItem(id, location, imagePath));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.dataset = wishlist;
    }

    public List<WishlistItem> getDataset() {
        return dataset;
    }
}

