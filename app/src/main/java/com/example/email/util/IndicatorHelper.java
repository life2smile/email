package com.example.email.util;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.email.R;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class IndicatorHelper {
    final static List<ImageView> indicators = new ArrayList<>();

    public static void generateIndicator(Activity activity, int size) {
        LinearLayout indicatorLayout = activity.findViewById(R.id.ll_indicator);
        for (int i = 0; i < size; i++) {
            ImageView indicate = new ImageView(activity);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.height = 36;
            params.width = 36;
            params.leftMargin = 15;
            indicate.setLayoutParams(params);
            indicate.setImageResource(R.mipmap.fullstop);
            indicators.add(indicate);
            indicatorLayout.addView(indicate);
        }
        indicators.get(0).setImageResource(R.mipmap.separators);
    }

    public static void updateStatus(int position) {
        for (int i = 0; i < indicators.size(); i++) {
            ImageView indicator = indicators.get(i);
            indicator.setImageResource(position == i ? R.mipmap.separators : R.mipmap.fullstop);
        }
    }
}