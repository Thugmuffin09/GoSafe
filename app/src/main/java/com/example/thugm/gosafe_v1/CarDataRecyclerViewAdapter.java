package com.example.thugm.gosafe_v1;

import android.graphics.Color;
import android.support.v7.util.AsyncListUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Author: Saimir Sulaj
 * Date: December 27, 2016
 * Purpose: Adapter for grid to display OBDII data.
 */

public class CarDataRecyclerViewAdapter extends RecyclerView.Adapter {

    private static final int MAX_WORDS_PER_LINE = 1;
    private static final int LABEL_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    private ArrayList<CarData> dataSet;

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView dataLabel;
        public TextView value;
        public ProgressBar progressive;
        public TextView health;
        public RecyclerViewHolder(View view) {
            super(view);
            dataLabel = (TextView) view.findViewById(R.id.detail_page_data_label);
            value = (TextView) view.findViewById(R.id.detail_page_value);
            progressive = (ProgressBar) view.findViewById(R.id.progressive);
            health = (TextView) view.findViewById(R.id.healthStatus);
        }
    }

    public CarDataRecyclerViewAdapter(ArrayList<CarData> data) { this.dataSet = data; }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detail_page_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CarData dataCouple = dataSet.get(position);
        RecyclerViewHolder holder_ = ((RecyclerViewHolder) holder);
        holder_.dataLabel.setText(dataCouple.Name);
        holder_.value.setText(dataCouple.Value);
        if(dataCouple.HealthType == CarData.HealthTypes.None)
        {
            holder_.progressive.setVisibility(View.GONE);
            holder_.health.setVisibility(View.GONE);
        }
        else if(dataCouple.HealthType == CarData.HealthTypes.Progressive)
        {
            holder_.progressive.setVisibility(View.VISIBLE);
            holder_.progressive.setProgress(dataCouple.Health);
            holder_.health.setVisibility(View.GONE);
        }
        else
        {
            holder_.progressive.setVisibility(View.GONE);
            holder_.health.setVisibility(View.VISIBLE);
            if(dataCouple.HealthType == CarData.HealthTypes.Boolean)
            {
                String healthText = dataCouple.Health == 0 ? "ERROR" : "Working Fine";
                int textColor = dataCouple.Health == 0 ? Color.RED : Color.GREEN;
                holder_.health.setText(healthText);
                holder_.health.setTextColor(textColor);
            }
            else // twoStep
            {
                String healthText = dataCouple.Health == 0 ? "ERROR" : (dataCouple.Health == 1 ? "Needs Attention" : "Working Fine");
                int textColor = dataCouple.Health == 0 ? Color.RED : (dataCouple.Health == 1 ? Color.YELLOW : Color.GREEN);
                holder_.health.setText(healthText);
                holder_.health.setTextColor(textColor);
            }
        }
    }

    @Override
    public int getItemCount() { return dataSet.size(); }
}
