package com.example.email.view.viewpageritem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.email.R;
import com.example.email.util.Util;
import com.example.email.viewmodel.bean.HomeData;

public class ItemImageItemViewProvider implements IItemViewProvider {
    @Override
    public View getView(Context context, HomeData homeData) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.viewpager_item_image_view, null, false);
        updateView(view, homeData);
        return view;
    }

    private void updateView(View view, HomeData homeData) {
        ImageView bannerIv = view.findViewById(R.id.iv_banner_item);
        RequestOptions options = RequestOptions
                .bitmapTransform(new RoundedCorners(Util.dp2Px(view.getContext(), 24)));
        Glide.with(view).load(homeData.getImgUrl()).apply(options).into(bannerIv);
    }
}