package com.sky.journeys.skyjourneys.controllers;

import android.app.Activity;
import android.content.Context;

import com.sky.journeys.skyjourneys.interfaces.APIResponseHandler;
import com.sky.journeys.skyjourneys.models.Customer;

import org.json.JSONObject;

public class ProfileResponseHandler implements APIResponseHandler {
    private String queryResponse;
    private Customer customer;
    private Activity activity;
    private Context context;

    public ProfileResponseHandler(String queryResponse, Activity activity, Context context) {
        this.queryResponse = queryResponse;
        this.activity = activity;
        this.context = context;

        handleAPIResponse(queryResponse);
    }

    @Override
    public void handleAPIResponse(String APIResponse) {
        if (APIResponse != null && !APIResponse.equals("")) {
            try {
                JSONObject jsonObject = new JSONObject(APIResponse);
                JSONObject user = jsonObject.getJSONObject("user");

                    int id = user.getInt("id");
                    String profileImage = user.getString("profile-picture");

                    Customer customer = new Customer(id, profileImage);
                    this.customer = customer;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Customer getCustomer() {
        return customer;
    }
}
