package com.example.email.view.detail.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.email.R;
import com.example.email.bean.DetailData;
import com.example.email.view.detail.viewpageritem.PagerViewProvider;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

public class DetailViewPagerAdapter extends PagerAdapter {
    private List<DetailData> mDetailDataList;

    public void setDataList(List<DetailData> dataList) {
        mDetailDataList = dataList;
        notifyDataSetChanged();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mDetailDataList.get(position).getTitle();
    }

    @Override
    public int getCount() {
        return mDetailDataList == null ? 0 : mDetailDataList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = new PagerViewProvider().getView(container.getContext(), mDetailDataList.get(position));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}