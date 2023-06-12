package com.example.email.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.email.R;
import com.example.email.bean.DetailData;
import com.example.email.view.viewpageritem.ViewProvider;
import com.example.email.viewmodel.DetailViewModel;
import com.example.email.viewmodel.HomeViewModel;
import com.google.android.material.appbar.AppBarLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class DetailActivity extends AppCompatActivity {
    private DetailViewModel mDetailViewModel;
    private AppBarLayout mAppBarLayout;

    private TextView mTitleTv;
    private TextView mFinalTv;

    private ImageView mBackIv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_activity_layout);
        intiView();
        initData();
    }

    private void intiView() {
        mAppBarLayout = findViewById(R.id.appbar_layout);
        mTitleTv = findViewById(R.id.tv_tool_bar_title);
        mFinalTv = findViewById(R.id.tv_final_title);
        mBackIv = findViewById(R.id.iv_back);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int totalScrollRange = appBarLayout.getTotalScrollRange();
                float ratio = Math.abs((float) verticalOffset / totalScrollRange);

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
        mDetailViewModel.getDetailData().observeForever(new Observer<DetailData>() {
            @Override
            public void onChanged(DetailData detailData) {

            }
        });
        mDetailViewModel.requestData();
    }
}