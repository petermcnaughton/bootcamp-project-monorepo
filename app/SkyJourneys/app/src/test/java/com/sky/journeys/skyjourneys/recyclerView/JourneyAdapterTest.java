package com.sky.journeys.skyjourneys.recyclerView;

import android.content.Context;

import com.sky.journeys.skyjourneys.models.CurrentJourneyResult;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class JourneyAdapterTest {
    private Context context;
    private List<CurrentJourneyResult> dataset;
    JourneyAdapter adapter;

    @Before
    public void setUp() {
        context = RuntimeEnvironment.application;
        dataset = new ArrayList<>();
        dataset.add(
                new CurrentJourneyResult("1", "London", "Valencia", "01/01/2018", "02/02/2018", "http://example.com"));
        adapter = new JourneyAdapter(context, dataset);
    }

    @Test
    public void constructorSetsFieldsCorrectly() {
        assertEquals(context, adapter.getContext());
        assertEquals(dataset, adapter.getJourneys());
    }

    @Test
    public void getItemCountReturnsCorrectNumberOfItems() {
        assertEquals(1, adapter.getItemCount());
    }

}