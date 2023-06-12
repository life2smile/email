package com.example.email.view.home;

import android.view.View;
import android.view.ViewGroup;

import com.example.email.bean.HomeData;
import com.example.email.view.viewpager.PagerAdapter;

import java.util.List;

import androidx.annotation.NonNull;

public class HomeViewPagerAdapter extends PagerAdapter {
    private List<HomeData> mHomeDataList;

    public void setDataList(List<HomeData> dataList) {
        mHomeDataList = dataList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mHomeDataList == null ? 0 : mHomeDataList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = ViewProvider.ViewFactory.createView(container.getContext(),
                mHomeDataList.get(position));
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