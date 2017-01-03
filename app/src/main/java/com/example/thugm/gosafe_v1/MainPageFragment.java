package com.example.thugm.gosafe_v1;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Author: Saimir Sulaj
 * Date: December 27, 2016
 * Purpose: Fragment for main page.
 */

public class MainPageFragment extends Fragment {

    BaseCom baseCom;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        baseCom = (BaseCom) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_page_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int dayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        CycleDurationArc cycleProgressArc = (CycleDurationArc) view.findViewById(R.id.cycle_progress_arc);
        TextView daysLeft = (TextView) view.findViewById(R.id.days_left);
        TextView daysLeftInfo = (TextView) view.findViewById(R.id.days_left_info);
        LinearLayout toDataButton = (LinearLayout) view.findViewById(R.id.to_notif_button);

        cycleProgressArc.start(dayOfMonth);
        daysLeft.setText(Integer.toString(CycleDurationArc.daysInMonth(dayOfMonth) - dayOfMonth));
        daysLeftInfo.setText("days left in cycle (" + DateFormat.months[Calendar.getInstance().get(Calendar.MONTH)] + ")");
        toDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseCom.setPage(CustomFragmentPagerAdapter.NOTIFICATION_PAGE);
            }
        });
    }
}
