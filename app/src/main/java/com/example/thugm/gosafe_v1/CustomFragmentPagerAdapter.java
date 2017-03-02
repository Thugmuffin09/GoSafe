package com.example.thugm.gosafe_v1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Author: Saimir Sulaj
 * Date: December 27, 2016
 * Purpose: Adapter for Pager object, allows a scrollable array of fragments.
 */

public class CustomFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES  = 3;
    public static final int MAIN_PAGE = 1;
    public static final int NOTIFICATION_PAGE = 0;
    public static final int DATA_PAGE = 2;

    public CustomFragmentPagerAdapter (FragmentManager fm) { super(fm); }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case MAIN_PAGE: return new MainPageFragment();
            case NOTIFICATION_PAGE: return new NotifPageFragment();
            default: return new DetailPageFragment();
        }
    }
}