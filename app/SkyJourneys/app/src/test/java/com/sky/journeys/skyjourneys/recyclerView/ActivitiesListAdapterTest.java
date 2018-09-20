package com.sky.journeys.skyjourneys.recyclerView;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sky.journeys.skyjourneys.R;
import com.sky.journeys.skyjourneys.models.ActivitiesListResult;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ActivitiesListAdapterTest {

    //test class variables
    private ActivitiesListAdapter activitiesListAdapter;
    private Picasso mockPicasso;
    private ActivitiesListResult activitySampleTest;

    @Before
    public void setUp() {
        //Step 1: creating sample result data to pass into the activitiesListAdapter constructor
        activitySampleTest = new ActivitiesListResult("1","Swimming", "20-12-2018", "Madrid", "MadridImage");
        ArrayList<ActivitiesListResult> testActivitiesList = new ArrayList<>();
        testActivitiesList.add(activitySampleTest);

        //Step 2: creating mock instance of picasso
        mockPicasso = mock(Picasso.class);
        //Step 3: creating instance of the adapter we want to test and pass it the samples we created.
        activitiesListAdapter = new ActivitiesListAdapter(null, testActivitiesList, mockPicasso);

    }


    @Test
    public void getItemCountReturnsNumberofItemsInActivitiesList() {
        //given


        //when
        int result = activitiesListAdapter.getItemCount();

        //then
        assertEquals(1, result);


    }

    @Test
    public void onBindViewHolderSetsViewHolderValues() {
        //given;
        int testPosition = 0;
        //Step 4: creating mock instances of our view holder view
        CardView mockIdCardView = mock(CardView.class);
        TextView mockTitleTextView = mock(TextView.class);
        TextView mockCityTextView = mock(TextView.class);
        TextView mockDateTextView = mock(TextView.class);
        ImageView mockImageImageView = mock(ImageView.class);

        //Step 5: mocking the step where we match the views with their place in the xml file
        View mockView = mock(View.class);
        when(mockView.findViewById(R.id.id)).thenReturn(mockIdCardView);
        when(mockView.findViewById(R.id.title)).thenReturn(mockTitleTextView);
        when(mockView.findViewById(R.id.city)).thenReturn(mockCityTextView);
        when(mockView.findViewById(R.id.date)).thenReturn(mockDateTextView);
        when(mockView.findViewById(R.id.image)).thenReturn(mockImageImageView);

        //Step 6: we feed the views into the mock holder with mockView
        ActivitiesListAdapter.ActivitiesViewHolder testHolder = new ActivitiesListAdapter.ActivitiesViewHolder(mockView);
        //Step 7: mocking the picasso methods which includes load(), and resize().
        RequestCreator requestCreator = mock(RequestCreator.class);
        when(mockPicasso.load("MadridImage")).thenReturn(requestCreator);
        when(requestCreator.resize(400, 300)).thenReturn(requestCreator);


        //when
        //Step 8: calling the method we want to test on activitiesListAdapter instance which we created
        activitiesListAdapter.onBindViewHolder(testHolder, testPosition);

        //then
        // Step 9: we verify the interaction of our mocks in the method under test
        verify(mockIdCardView).setTag(activitySampleTest);
        verify(mockTitleTextView).setText("Swimming");
        verify(mockCityTextView).setText("Madrid");
        verify(mockDateTextView).setText("20-12-2018");
        verify(mockPicasso).load("MadridImage");
        verify(requestCreator).resize(400, 300);
        verify(requestCreator).into(mockImageImageView);

    }

}