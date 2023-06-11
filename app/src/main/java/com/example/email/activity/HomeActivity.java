package com.example.email.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.email.R;
import com.example.email.util.IndicatorHelper;
import com.example.email.view.viewpager.BounceBackViewPager;
import com.example.email.view.viewpager.ViewPagerAdapter;
import com.example.email.viewmodel.HomeViewModel;
import com.example.email.viewmodel.bean.HomeData;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

public class HomeActivity extends AppCompatActivity {
    private BounceBackViewPager mBannerViewPager;
    private ViewPagerAdapter mViewPagerAdapter;

    private HomeViewModel mHomeViewModel;

    private boolean detailPageOpened = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_layout);
        initViews();
        intData();
        registerListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        detailPageOpened = false;
    }

    private void initViews() {
        mViewPagerAdapter = new ViewPagerAdapter();
        mBannerViewPager = findViewById(R.id.viewpager_banner);
        mBannerViewPager.setAdapter(mViewPagerAdapter);
    }

    private void intData() {
        mHomeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        mHomeViewModel.getHomeData().observe(this, new Observer<List<HomeData>>() {
            @Override
            public void onChanged(List<HomeData> dataList) {
                mViewPagerAdapter.setDataList(dataList);
                IndicatorHelper.generateIndicator(HomeActivity.this, dataList.size());
            }
        });
        mHomeViewModel.requestData();
    }


    private void registerListeners() {
        mBannerViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                IndicatorHelper.updateStatus(position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                int lastIndex = mHomeViewModel.getHomeData().getValue().size() - 2;
                if (!detailPageOpened && position == lastIndex && positionOffset > 0.2) {
                    detailPageOpened = true;
                    Intent intent = new Intent(HomeActivity.this, DetailActivity.class);
                    startActivity(intent);
                    mBannerViewPager.setCurrentItem(lastIndex);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }
}