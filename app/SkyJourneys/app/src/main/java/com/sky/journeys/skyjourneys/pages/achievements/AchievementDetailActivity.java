package com.sky.journeys.skyjourneys.pages.achievements;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sky.journeys.skyjourneys.R;
import com.sky.journeys.skyjourneys.controllers.AchievementResources;
import com.sky.journeys.skyjourneys.controllers.IntentDataHandler;

public class AchievementDetailActivity extends AppCompatActivity {
    private TextView achievementNameTextView;
    private TextView achievementDescTextView;
    private TextView achievementPrizeTextView;
    private ImageView image;
    private RelativeLayout background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement_detail);
        setViews();

        IntentDataHandler handler = new IntentDataHandler(this);

        achievementNameTextView.setText(handler.getIntentData("name"));
        achievementDescTextView.setText(handler.getIntentData("description"));
        achievementPrizeTextView.append(handler.getIntentData("prize"));
//        handler.setTextOfTextView(achievementNameTextView, handler.getIntentData("name"));
//        handler.setTextOfTextView(achievementDescTextView, handler.getIntentData("description"));
//        handler.setTextOfTextView(achievementPrizeTextView, handler.getIntentData("prize"));
        handler.setIconImage(image, handler.getIntentData("prizeImage"));
        background.setBackgroundColor(this.getResources().getColor(new AchievementResources(handler.getIntentIntData("id")).getColor()));
    }

    public void setViews() {
        achievementNameTextView = (TextView) findViewById(R.id.achievement_name);
        achievementDescTextView = (TextView) findViewById(R.id.achievement_description);
        achievementPrizeTextView = (TextView) findViewById(R.id.achievement_prize);
        image = (ImageView) findViewById(R.id.image);
        background = (RelativeLayout) findViewById(R.id.background);
    }
}
