package com.example.thugm.gosafe_v1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class DetailPageFragment extends Fragment {

    private static final int GRID_SPAN = 2;

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
        View view = inflater.inflate(R.layout.detail_page_fragment, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.detail_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getContext(), GRID_SPAN);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Dummy data generation.
        ArrayList<CarData> dummyData = new ArrayList<>();
        dummyData.add(new CarData("Engine Temperature", "154°C"));
        dummyData.add(new CarData("Max RPMS",           "1200 RPM"));
        dummyData.add(new CarData("Max Speed",          "300 KPH"));
        dummyData.add(new CarData("Intake Air Temp",    "0°C", CarData.HealthTypes.Progressive, 20));
        dummyData.add(new CarData("Oil Level",          "2021mL", CarData.HealthTypes.TwoStep, 1));
        dummyData.add(new CarData("Gas Level",          "10000mL", CarData.HealthTypes.TwoStep, 0));
        dummyData.add(new CarData("Air Filter",         "", CarData.HealthTypes.TwoStep, 2));
        dummyData.add(new CarData("Something Else",     "300 KPH", CarData.HealthTypes.Boolean, 0));
        dummyData.add(new CarData("Something Else",     "300 KPH", CarData.HealthTypes.Boolean, 1));



        mAdapter = new CustomRecyclerViewAdapter(dummyData);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button viewMapData = (Button) view.findViewById(R.id.map_button);
        LinearLayout toHomeButton = (LinearLayout) view.findViewById(R.id.to_main_button);

        viewMapData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MapsActivity.class);
                getContext().startActivity(intent);
            }
        });
        toHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseCom.setPage(CustomFragmentPagerAdapter.MAIN_PAGE);
            }
        });
    }
}
