package com.example.email.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.email.R;
import com.example.email.bean.DetailData;
import com.example.email.view.detail.adapter.DetailViewPagerAdapter;
import com.example.email.viewmodel.DetailViewModel;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

public class DetailActivity extends AppCompatActivity {
    private DetailViewModel mDetailViewModel;
    private AppBarLayout mAppBarLayout;

    private ImageView mBackIv;

    private TabLayout mTableLayout;

    private ViewPager mViewPager;

    private TextView mDayUpdateTv;

    private DetailViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_activity_layout);
        intiView();
        initData();
    }

    private void intiView() {
        mAppBarLayout = findViewById(R.id.appbar_layout);
        mBackIv = findViewById(R.id.iv_back);
        mTableLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
        mDayUpdateTv = findViewById(R.id.tv_day_update);
        mAdapter = new DetailViewPagerAdapter();
        mViewPager.setAdapter(mAdapter);
        mTableLayout.setupWithViewPager(mViewPager);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int totalScrollRange = appBarLayout.getTotalScrollRange();
                float ratio = Math.abs((float) verticalOffset / totalScrollRange);
                mDayUpdateTv.setAlpha(1 - ratio);
            }
        });
        mBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initData() {
        mDetailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        mDetailViewModel.getDetailDataList().observeForever(new Observer<List<DetailData>>() {
            @Override
            public void onChanged(List<DetailData> detailData) {
                mAdapter.setDataList(detailData);
            }
        });
        mDetailViewModel.requestData();
    }
}