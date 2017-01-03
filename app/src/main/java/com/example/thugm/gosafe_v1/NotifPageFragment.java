package com.example.thugm.gosafe_v1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Author: Saimir Sulaj
 * Date: December 27, 2016
 * Purpose: Fragment for detail page, shows data read from OBDII sensor.
 */

public class NotifPageFragment extends Fragment {

    private static final int GRID_SPAN = 1;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    BaseCom baseCom;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        baseCom = (BaseCom) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notif_page_fragment, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.notif_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getContext(), GRID_SPAN);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Dummy data generation.
        ArrayList<NotificationItem> dummyData = new ArrayList<>();
        dummyData.add(new NotificationItem("TIME FOR AN OIL CHANGE", "EYY LMAO"));


        mAdapter = new NotificationRecyclerViewAdapter(dummyData);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout toHomeButton = (LinearLayout) view.findViewById(R.id.to_main_button);
        LinearLayout toDetailsButton = (LinearLayout) view.findViewById(R.id.to_details_button);


        toHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseCom.setPage(CustomFragmentPagerAdapter.MAIN_PAGE);
            }
        });
        toDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseCom.setPage(CustomFragmentPagerAdapter.DATA_PAGE);
            }
        });
    }
}
