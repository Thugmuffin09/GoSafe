package com.example.thugm.gosafe_v1;

import android.graphics.drawable.Drawable;

/**
 * Created by Felix on 1/3/2017.
 */

public class NotificationItem {

    public String Title;
    public String Content;
    public int Icon;

    public NotificationItem(String title, String cont, int icon)
    {
        Title = title;
        Content = cont;
        Icon = icon;
    }

}
