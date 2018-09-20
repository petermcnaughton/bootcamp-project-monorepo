package com.sky.journeys.skyjourneys.utilities;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URL;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class NetworkUtilsTest {

    @Test
    public void buildUrlReturnsCorrectResult() {
        String urlString = "http://www.example.com";
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(url, NetworkUtils.buildUrl(urlString));
    }
}