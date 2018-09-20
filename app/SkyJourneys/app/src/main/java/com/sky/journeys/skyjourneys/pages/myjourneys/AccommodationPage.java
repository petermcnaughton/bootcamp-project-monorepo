package com.sky.journeys.skyjourneys.pages.myjourneys;


import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sky.journeys.skyjourneys.R;
import com.sky.journeys.skyjourneys.models.AccommodationResult;
import com.sky.journeys.skyjourneys.models.CurrentJourneyResult;
import com.sky.journeys.skyjourneys.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class AccommodationPage extends AppCompatActivity implements OnMapReadyCallback {

    private TextView hotelName;

    private TextView roomType;
    private TextView numberOfPeople;
    private TextView nights;
    private TextView hotelAddress;
    private TextView checkInDate;
    private TextView checkOutDate;
    private TextView checkInTime;
    private TextView checkOutTime;
    private ImageView hotelImage;
    private String hotelImagePath;

        private GoogleMap mMap;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_accomodation_page);
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

            hotelName = (TextView) findViewById(R.id.hotelName);

            roomType = (TextView) findViewById(R.id.roomType);
            numberOfPeople = (TextView) findViewById(R.id.numberOfPeople);
            nights = (TextView) findViewById(R.id.nights);
            hotelAddress = (TextView) findViewById(R.id.hotelAddress);
            checkInDate = (TextView) findViewById(R.id.checkInDate);
            checkOutDate= (TextView) findViewById(R.id.checkOutDate);
            checkInTime = (TextView) findViewById(R.id.checkInTime);
            checkOutTime= (TextView) findViewById(R.id.checkOutTime);
            hotelImage = (ImageView) findViewById(R.id.hotelImage);

            URL accommodationSearchURL = NetworkUtils.buildUrl("https://firebasestorage.googleapis.com/v0/b/journeys-6208e.appspot.com/o/Accomodation.json?alt=media&token=87d99860-e677-4c8b-b85c-5715794c9b97");
            String results;
            new AccommodationQuery().execute(accommodationSearchURL);

        }

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera. In this case,
         * we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to install
         * it inside the SupportMapFragment. This method will only be triggered once the user has
         * installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;

            // Add a marker in Sydney and move the camera
            LatLng Istanbul = new LatLng(41.0100389, 28.977010999999948);
            mMap.addMarker(new MarkerOptions().position(Istanbul).title("Marker in Istanbul"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(Istanbul));}

        public class AccommodationQuery extends AsyncTask<URL, Void, String> {

            @Override
            protected String doInBackground(URL... params) {
                URL searchUrl = params[0];

                String accommodationSearchResult = null;

                try {

                    accommodationSearchResult = NetworkUtils.getResponseFromHttpUrl(searchUrl);

                } catch (IOException e) {

                    e.printStackTrace();
                }
                return accommodationSearchResult;
            }

            @Override
            protected void onPostExecute(String responseJson) {

                if (responseJson != null && !responseJson.equals("")) {

                    try {

                        //parse json
                        JSONObject jsonObject = new JSONObject(responseJson);

                        JSONObject accommodation = jsonObject.getJSONObject("accommodation");
                        hotelName.setText(accommodation.getString("hotel-name"));
                        roomType.setText(accommodation.getString("room-type"));
                        numberOfPeople.setText(accommodation.getString("number of guests"));
                        nights.setText(accommodation.getString("duration"));
                        hotelAddress.setText(accommodation.getString("address"));
                        checkInDate.setText(accommodation.getString("check-in-date"));
                        checkOutDate.setText(accommodation.getString("check-out-date"));
                        checkInTime.append(accommodation.getString("check-in-time"));
                        checkOutTime.append(accommodation.getString("check-out-time"));
                        hotelImagePath = accommodation.getString("hotel-image");

                        Picasso.get().load(hotelImagePath).into(hotelImage);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }


        }
}
