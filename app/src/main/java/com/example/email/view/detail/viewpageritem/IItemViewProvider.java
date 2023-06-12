package com.example.email.view.detail.viewpageritem;

import android.content.Context;
import android.view.View;

import com.example.email.bean.HomeData;

public interface IItemViewProvider {
    View getView(Context context, HomeData homeData);
}