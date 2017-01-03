package com.example.thugm.gosafe_v1;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Author: Saimir Sulaj
 * Date: December 27, 2016
 * Purpose: Adapter for grid to display OBDII data.
 */

public class NotificationRecyclerViewAdapter extends RecyclerView.Adapter {

    private static final int MAX_WORDS_PER_LINE = 1;
    private static final int LABEL_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    private ArrayList<NotificationItem> dataSet;

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView content;
        public RecyclerViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.notif_title);
            content = (TextView) view.findViewById(R.id.notif_content);
        }
    }

    public NotificationRecyclerViewAdapter(ArrayList<NotificationItem> data) { this.dataSet = data; }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notif_page_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NotificationItem dataCouple = dataSet.get(position);
        ((RecyclerViewHolder)holder).title.setText(dataCouple.Title);
        ((RecyclerViewHolder)holder).content.setText(dataCouple.Content);
    }

    @Override
    public int getItemCount() { return dataSet.size(); }
}
