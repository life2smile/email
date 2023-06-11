package com.example.email.viewmodel;

import android.app.Application;

import com.example.email.viewmodel.bean.HomeData;
import com.example.email.viewmodel.bean.ViewType;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class HomeViewModel extends AndroidViewModel {
    private MutableLiveData<List<HomeData>> mHomeData;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        mHomeData = new MutableLiveData<>();
    }

    public MutableLiveData<List<HomeData>> getHomeData() {
        return mHomeData;
    }

    public void requestData() {
        //模拟网络请求
        List<HomeData> dataList = new ArrayList<>();
        dataList.add(new HomeData(ViewType.BANNER_IMAGE, "https://lmg.jj20.com/up/allimg/1113/030120113404/200301113404-1-1200.jpg", "第1个标题", "第1个描述"));
        dataList.add(new HomeData(ViewType.BANNER_IMAGE, "https://lmg.jj20.com/up/allimg/1115/111421103553/211114103553-1-1200.jpg", "第2个标题", "第2个描述"));
        dataList.add(new HomeData(ViewType.BANNER_IMAGE, "https://lmg.jj20.com/up/allimg/1111/0Z31Q41046/1PZ3141046-1-1200.jpg", "第3个标题", "第3个描述"));

        dataList.add(new HomeData(ViewType.BANNER_MORE));

        mHomeData.postValue(dataList);

    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}