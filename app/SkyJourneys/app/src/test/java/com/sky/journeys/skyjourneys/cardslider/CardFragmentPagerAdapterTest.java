package com.sky.journeys.skyjourneys.cardslider;

import android.support.v4.app.FragmentManager;

import com.sky.journeys.skyjourneys.models.WishlistItem;
import com.sky.journeys.skyjourneys.pages.wishlist.Wishlist;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

public class CardFragmentPagerAdapterTest {
    private Wishlist activity;
    private CardFragmentPagerAdapter adapter;
    private List<WishlistItem> dataset;

    @Mock
    private Wishlist activityMock;

    @Mock
    private FragmentManager fragmentManagerMock;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        activity = mock(Wishlist.class);
        when(activityMock.getSupportFragmentManager()).thenReturn(fragmentManagerMock);
        dataset = new ArrayList<WishlistItem>();

        adapter = new CardFragmentPagerAdapter(activity.getSupportFragmentManager(), 2, dataset);
    }

    @Test
    public void cardFragmentAdapterClassIsInstantiatedCorrectly() {
        assertEquals(2, adapter.getBaseElevation(), 0);
        assertEquals(dataset, adapter.getDataset());
    }
}