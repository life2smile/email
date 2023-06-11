package com.example.email.util;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.email.R;

import java.util.ArrayList;
import java.util.List;

public class IndicatorHelper {
    final static List<ImageView> indicators = new ArrayList<>();

    public static void generateIndicator(Activity activity, int size) {
        LinearLayout indicatorLayout = activity.findViewById(R.id.viewpager_indicator_layout);
        for (int i = 0; i < size; i++) {
            ImageView indicate = new ImageView(activity);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.height = 30;
            params.width = 30;
            params.leftMargin = 15;
            indicate.setLayoutParams(params);
//            indicate.setImageResource(R.drawable.circle_unselected);
            indicators.add(indicate);
            indicatorLayout.addView(indicate);
        }
//        indicators.get(0).setImageResource(R.drawable.circle_selected);
    }

    public static void updateStatus(int position) {

    }
}