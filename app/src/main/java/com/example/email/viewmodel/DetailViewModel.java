package com.example.email.viewmodel;

import android.app.Application;

import com.example.email.bean.DetailData;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class DetailViewModel extends AndroidViewModel {
    private MutableLiveData<List<DetailData>> mDetailData;

    public DetailViewModel(@NonNull Application application) {
        super(application);
        mDetailData = new MutableLiveData<>();
    }

    public MutableLiveData<List<DetailData>> getDetailDataList() {
        return mDetailData;
    }

    public void requestData() {
        List<DetailData> tabs = new ArrayList<>();
        tabs.add(new DetailData("清醒钟", "1"));
        tabs.add(new DetailData("新鲜事", "1"));
        tabs.add(new DetailData("解压室", "1"));
        tabs.add(new DetailData("下饭菜", "1"));
        tabs.add(new DetailData("微兴趣", "1"));
        tabs.add(new DetailData("小剧场", "1"));
        getDetailDataList().postValue(tabs);
    }
}
