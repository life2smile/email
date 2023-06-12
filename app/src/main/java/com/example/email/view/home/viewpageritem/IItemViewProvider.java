package com.example.email.view.home.viewpageritem;

import android.content.Context;
import android.view.View;

import com.example.email.bean.HomeData;

public interface IItemViewProvider {
    View getView(Context context, HomeData homeData);
}