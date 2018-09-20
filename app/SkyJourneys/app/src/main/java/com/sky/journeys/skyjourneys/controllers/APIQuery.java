package com.sky.journeys.skyjourneys.controllers;

import android.os.AsyncTask;

import com.sky.journeys.skyjourneys.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class APIQuery extends AsyncTask<URL, Void, String> {

    @Override
    protected String doInBackground(URL... params) {
        URL requestUrl = params[0];
        String APIResponse = null;

        try {
            APIResponse = NetworkUtils.getResponseFromHttpUrl(requestUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return APIResponse;
    }
}
