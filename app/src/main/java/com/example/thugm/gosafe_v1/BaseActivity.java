package com.example.thugm.gosafe_v1;

import android.app.ActionBar;
import android.app.FragmentTransaction;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.util.TypedValue;
import android.util.Log;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.graphics.*;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

/**
 * Author: Saimir Sulaj
 * Date: December 27, 2016
 * Purpose: Background activity for application.
 */

public class BaseActivity extends AppCompatActivity implements BaseCom {

    ViewPager viewPager;
    public static Context applicationContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applicationContext = getApplicationContext();
        setContentView(R.layout.base_layout);

        CustomFragmentPagerAdapter customFragmentPagerAdapter = new CustomFragmentPagerAdapter(getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.base_pager);
        viewPager.setAdapter(customFragmentPagerAdapter);
        viewPager.setCurrentItem(1);

        final TabLayout mTabLayout = (TabLayout) findViewById(R.id.top_bar_tab);
        mTabLayout.setupWithViewPager(viewPager);



        mTabLayout.getTabAt(0).setIcon(R.drawable.ic_notifications);
        mTabLayout.getTabAt(1).setText("GoSafe");

        mTabLayout.getTabAt(2).setIcon(R.drawable.ic_car_details);

        Typeface antonTypeface = Typeface.createFromAsset(getAssets(), "fonts/Anton.ttf");

        ViewGroup slideTabStrip = (ViewGroup) mTabLayout.getChildAt(0);

        Resources r = getResources();
        int px = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, r.getDisplayMetrics());

        AppCompatTextView middleTab = (AppCompatTextView) ((ViewGroup) slideTabStrip.getChildAt(1)).getChildAt(1);
        Log.v("SOZO", String.valueOf(middleTab.getTextSize()));
        middleTab.setTypeface(antonTypeface, Typeface.NORMAL);
        middleTab.setTextSize(TypedValue.COMPLEX_UNIT_PX, 50);


        Log.v("SOZO", String.valueOf(middleTab.getPaddingLeft()));
        middleTab.requestLayout();

        ViewTreeObserver viewTreeObserver = mTabLayout.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    int margin = (mTabLayout.getWidth() / 9);

                    View leftTab = ((ViewGroup) mTabLayout.getChildAt(0)).getChildAt(0);
                    ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) leftTab.getLayoutParams();
                    p.setMargins(0, 0, margin, 0);
                    leftTab.requestLayout();

                    // Right one
                    View rightTab = ((ViewGroup) mTabLayout.getChildAt(0)).getChildAt(2);
                    ViewGroup.MarginLayoutParams p2 = (ViewGroup.MarginLayoutParams) rightTab.getLayoutParams();

                    p2.setMargins(margin, 0, 0, 0);
                    rightTab.requestLayout();

                    // Right one
                    View midTab = ((ViewGroup) mTabLayout.getChildAt(0)).getChildAt(1);
                    ViewGroup.MarginLayoutParams p3 = (ViewGroup.MarginLayoutParams) midTab.getLayoutParams();

                    //p3.setMargins(-margin / 2, 0, -margin / 2, 0);
                    rightTab.requestLayout();
                }
            });
        }

    }

    @Override
    public void setPage(int index) {
        viewPager.setCurrentItem(index);
    }
}
