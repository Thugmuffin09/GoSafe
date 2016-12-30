package com.example.thugm.gosafe_v1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Author: Saimir Sulaj
 * Date: December 27, 2016
 * Purpose: Adapter for grid to display OBDII data.
 */

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter {

    private static final int MAX_WORDS_PER_LINE = 1;
    private static final int LABEL_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    private ArrayList<String[]> dataSet;

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView dataLabel;
        public TextView value;
        public RecyclerViewHolder(View view) {
            super(view);
            dataLabel = (TextView) view.findViewById(R.id.detail_page_data_label);
            value = (TextView) view.findViewById(R.id.detail_page_value);
        }
    }

    public CustomRecyclerViewAdapter(ArrayList<String[]> data) { this.dataSet = data; }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detail_page_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String[] dataCouple = dataSet.get(position);
        ((RecyclerViewHolder) holder).dataLabel.setText(dataCouple[LABEL_INDEX]);
        ((RecyclerViewHolder) holder).value.setText(dataCouple[VALUE_INDEX]);
    }

    @Override
    public int getItemCount() { return dataSet.size(); }
}
