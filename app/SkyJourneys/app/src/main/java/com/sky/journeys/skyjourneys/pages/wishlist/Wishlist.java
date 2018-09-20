package com.sky.journeys.skyjourneys.pages.wishlist;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.sky.journeys.skyjourneys.R;
import com.sky.journeys.skyjourneys.cardslider.CardFragmentPagerAdapter;
import com.sky.journeys.skyjourneys.cardslider.ShadowTransformer;
import com.sky.journeys.skyjourneys.controllers.APIQuery;
import com.sky.journeys.skyjourneys.controllers.WishlistResponseHandler;
import com.sky.journeys.skyjourneys.models.WishlistItem;
import com.sky.journeys.skyjourneys.utilities.NetworkUtils;

import java.net.URL;
import java.util.List;


public class Wishlist extends AppCompatActivity {
    private static final String queryURL = "https://firebasestorage.googleapis.com/v0/b/journeys-6208e.appspot.com/o/wishlist.json?alt=media&token=4556425f-f006-497a-bf53-bb0e29d45fd1";
    private final Context context = this;
    private final Activity activity = this;
    private List<WishlistItem> dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        URL builtQueryURL = NetworkUtils.buildUrl(queryURL);
        String queryResponse = "";

        try {
            queryResponse = new APIQuery().execute(builtQueryURL).get();
        } catch (Exception e) {
            e.getStackTrace();
        }

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        WishlistResponseHandler handler = new WishlistResponseHandler(queryResponse, activity, context);
        this.dataset = handler.getDataset();

        CardFragmentPagerAdapter pagerAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(), dpToPixels(2, context), dataset);
        ShadowTransformer fragmentCardShadowTransformer = new ShadowTransformer(viewPager, pagerAdapter);
        fragmentCardShadowTransformer.enableScaling(true);

        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(false, fragmentCardShadowTransformer);
        viewPager.setOffscreenPageLimit(3);

    }

    /**
     * Change value in dp to pixels
     * @param dp
     * @param context
     * @return
     */
    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

}