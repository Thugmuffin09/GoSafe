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
 * Author: Felix Guo
 * Date: March 1, 2017
 * Purpose: Fragment for notification page, shows notifications for user
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
        dummyData.add(new NotificationItem("Time for an oil change!",
                "Our readings indicate your last oil change was over 2 months ago!", R.drawable.ic_oil_change));


        mAdapter = new NotificationRecyclerViewAdapter(dummyData);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }
}
