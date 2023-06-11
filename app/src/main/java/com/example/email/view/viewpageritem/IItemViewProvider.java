package com.example.email.view.viewpageritem;

import android.content.Context;
import android.view.View;

import com.example.email.viewmodel.bean.HomeData;

public interface IItemViewProvider {
    View getView(Context context, HomeData homeData);
}