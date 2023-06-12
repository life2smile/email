package com.example.email.viewmodel;

import android.app.Application;

import com.example.email.bean.DetailData;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class DetailViewModel extends AndroidViewModel {
    private MutableLiveData<DetailData> mDetailData;

    public DetailViewModel(@NonNull Application application) {
        super(application);
        mDetailData = new MutableLiveData<>();
    }

    public MutableLiveData<DetailData> getDetailData() {
        return mDetailData;
    }

    public void requestData() {
        List<String> tabs = new ArrayList<>();
        tabs.add("清醒钟");
        tabs.add("新鲜事");
        tabs.add("解压室");
        tabs.add("下饭菜");
        tabs.add("微兴趣");
        tabs.add("小剧场");
        DetailData detailData = new DetailData(tabs);
        getDetailData().postValue(detailData);
    }
}
