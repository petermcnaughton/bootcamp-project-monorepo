package com.sky.journeys.skyjourneys.utilities;

import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class NetworkUtilsTest {
    @Test
    public void getResponseFromHttpUrlReturnsValidResult() {
        URL url = null;
        String response = "";
        String expectedResponse = "{\n" +
                "  \"trips\": [\n" +
                "    {\n" +
                "      \"id\": \"1234\",\n" +
                "      \"from\": \"London\",\n" +
                "      \"to\": \"Istanbul\",\n" +
                "      \"outbound-date\": \"30/07/2018\",\n" +
                "      \"inbound-date\": \"07/08/2018\",\n" +
                "      \"image\": \"https://media.istockphoto.com/photos/glass-of-turkish-tea-in-front-of-blurred-istanbul-skyline-picture-id639257692?k=6&m=639257692&s=612x612&w=0&h=baGrS5o3G6Y96-GohdzQXtw86My7ZY101VNoIbmZ3Ks=\"\n" +
                "\n" +
                "\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        try {
            url = new URL("https://firebasestorage.googleapis.com/v0/b/journeys-6208e.appspot.com/o/journeys.json?alt=media&token=fd19983f-9238-497b-a99c-6fa368ffe5f6");
        } catch (MalformedURLException e) {
            e.getStackTrace();
        }

        try {
            response = NetworkUtils.getResponseFromHttpUrl(url);
        } catch (IOException e) {
            e.getStackTrace();
        }

        assertEquals(expectedResponse, response);
    }

}