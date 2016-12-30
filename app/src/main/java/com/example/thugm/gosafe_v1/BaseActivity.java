package com.example.thugm.gosafe_v1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * Author: Saimir Sulaj
 * Date: December 27, 2016
 * Purpose: Background activity for application.
 */

public class BaseActivity extends FragmentActivity implements BaseCom {

    ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);

        CustomFragmentPagerAdapter customFragmentPagerAdapter = new CustomFragmentPagerAdapter(getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.base_pager);
        viewPager.setAdapter(customFragmentPagerAdapter);
    }

    @Override
    public void setPage(int index) {
        viewPager.setCurrentItem(index);
    }
}
