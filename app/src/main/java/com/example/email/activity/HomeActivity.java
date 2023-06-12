package com.example.email.activity;

import android.os.Bundle;

import com.example.email.R;
import com.example.email.util.IndicatorHelper;
import com.example.email.view.viewpager.ViewPager;
import com.example.email.view.viewpager.ViewPagerAdapter;
import com.example.email.viewmodel.HomeViewModel;
import com.example.email.viewmodel.OverScrollModel;
import com.example.email.bean.HomeData;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class HomeActivity extends AppCompatActivity {
    private ViewPager mBannerViewPager;
    private ViewPagerAdapter mViewPagerAdapter;

    private HomeViewModel mHomeViewModel;

    private OverScrollModel mOverScrollModel;

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
    }

    private void initViews() {
        mViewPagerAdapter = new ViewPagerAdapter();
        mBannerViewPager = findViewById(R.id.viewpager_banner);
        mBannerViewPager.needLastPageBound(true);
        mBannerViewPager.setAdapter(mViewPagerAdapter);
    }

    private void intData() {
        mOverScrollModel = new ViewModelProvider(this).get(OverScrollModel.class);
        mHomeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        mHomeViewModel.getHomeData().observe(this, new Observer<List<HomeData>>() {
            @Override
            public void onChanged(List<HomeData> dataList) {
                mViewPagerAdapter.setDataList(dataList);
                IndicatorHelper.generateIndicator(HomeActivity.this, dataList.size());
            }
        });
        mHomeViewModel.requestData();
        getLifecycle().addObserver(mOverScrollModel);
    }


    private void registerListeners() {
        mBannerViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                IndicatorHelper.updateStatus(position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // 倒数第二张图片实现弹回效果
                if (position == mViewPagerAdapter.getCount() - 2) {
                    mOverScrollModel.getPositionOffset().setValue(positionOffset);
                    mOverScrollModel.getPositionOffsetPixels().setValue(positionOffsetPixels);
                }
            }
        });
    }
}