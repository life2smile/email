package com.example.email.view.viewpageritem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.email.R;
import com.example.email.viewmodel.bean.HomeData;

public class ItemMoreItemViewProvider implements IItemViewProvider {
    @Override
    public View getView(Context context, HomeData homeData) {
        return LayoutInflater.from(context)
                .inflate(R.layout.viewpager_item_more_view, null, false);
    }
}