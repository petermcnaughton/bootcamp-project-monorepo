package com.sky.journeys.skyjourneys.cardslider;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;

import com.sky.journeys.skyjourneys.interfaces.CardAdapter;
import com.sky.journeys.skyjourneys.models.WishlistItem;

import java.util.ArrayList;
import java.util.List;

public class CardFragmentPagerAdapter extends FragmentStatePagerAdapter implements CardAdapter {
    private List<WishlistItem> dataset;
    private List<CardFragment> fragments;
    private float baseElevation;

    public CardFragmentPagerAdapter(FragmentManager fm, float baseElevation, List<WishlistItem> dataset) {
        super(fm);
        fragments = new ArrayList<>();
        this.baseElevation = baseElevation;
        this.dataset = dataset;

        for(int i = 0; i < dataset.size(); i++){
            CardFragment fragment = new CardFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("id", dataset.get(i).getId());
            bundle.putString("location", dataset.get(i).getLocation());
            bundle.putString("image", dataset.get(i).getImage());
            fragment.setArguments(bundle);
            addCardFragment(fragment);
        }
    }

    @Override
    public float getBaseElevation() {
        return baseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return fragments.get(position).getCardView();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return CardFragment.getInstance(position, dataset);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object fragment = super.instantiateItem(container, position);
        fragments.set(position, (CardFragment) fragment);
        return fragment;
    }

    public void addCardFragment(CardFragment fragment) {
        fragments.add(fragment);
    }

    public List<WishlistItem> getDataset() {
        return dataset;
    }
}
