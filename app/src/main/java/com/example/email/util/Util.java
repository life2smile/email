package com.example.email.util;

import android.content.Context;

public class Util {
    public static int dp2Px(Context context, int value) {
        int scale = (int) context.getResources().getDisplayMetrics().density;
        return (int) (value * scale + 0.5f);
    }
}