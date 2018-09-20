package com.sky.journeys.skyjourneys.pages.myjourneys;


import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sky.journeys.skyjourneys.R;
import com.sky.journeys.skyjourneys.models.FlightResult;
import com.sky.journeys.skyjourneys.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Flight extends AppCompatActivity {
    private TextView fromCity;
    private TextView toCity;
    private TextView fromAirport;
    private TextView toAirport;
    private TextView flightNumber;
    private TextView seatNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_page);

        URL flightSearchResult = NetworkUtils.buildUrl("http://34.254.142.16:8080/api/details/flight/12345");
        String results;
        new FlightQuery().execute(flightSearchResult);

        fromCity = (TextView) findViewById(R.id.fromCity);
        toCity = (TextView) findViewById(R.id.toCity);
        fromAirport = (TextView) findViewById(R.id.fromAirport);
        toAirport = (TextView) findViewById(R.id.toAirport);
        flightNumber = (TextView) findViewById(R.id.flightNumber);



    }




    public class FlightQuery extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];

            String flightsSearchResult = null;

            try {

                flightsSearchResult = NetworkUtils.getResponseFromHttpUrl(searchUrl);

            } catch (IOException e) {

                e.printStackTrace();
            }
            return flightsSearchResult;
        }

        @Override
        protected void onPostExecute(String responseJson) {

            if (responseJson != null && !responseJson.equals("")) {

                try {

                    //parse json
                    JSONObject jsonObject = new JSONObject(responseJson);
                    JSONArray outboundSegments = jsonObject.getJSONObject("outbound").getJSONArray("segments");
                    JSONArray returnboundSegments = jsonObject.getJSONObject("returnbound").getJSONArray("segments");


                    //extract fields
                    for (int i = 0; i < outboundSegments.length(); i++) {

                        JSONObject outboundFlight = outboundSegments.getJSONObject(i);


                        JSONObject returnboundFlight = returnboundSegments.getJSONObject(i);
                        fromCity.setText(outboundFlight.getString("arrivalAirportName"));
                        fromAirport.setText(outboundFlight.getString("arrivalAirportIATA"));
                        flightNumber.setText(outboundFlight.getString("flightNumber"));
                    }
                    for (int i = 0; i < returnboundSegments.length(); i++) {
                        JSONObject returnboundFlight = returnboundSegments.getJSONObject(i);
                        toCity.setText(returnboundFlight.getString("arrivalAirportName"));
                        toAirport.setText(returnboundFlight.getString("arrivalAirportIATA"));




                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
