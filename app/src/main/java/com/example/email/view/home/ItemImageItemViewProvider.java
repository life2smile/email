package com.example.email.view.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.email.R;
import com.example.email.util.Util;
import com.example.email.bean.HomeData;
import com.example.email.view.detail.viewpageritem.IItemViewProvider;

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

        GlideUrl glideUrl = new GlideUrl(homeData.getImgUrl(), new LazyHeaders.Builder()
                .addHeader("User-Agent",
                        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit / 537.36(KHTML, like Gecko) Chrome  47.0.2526.106 Safari / 537.36")
                .build());
        Glide.with(view)
                .load(glideUrl).apply(options).into(bannerIv);
    }
}