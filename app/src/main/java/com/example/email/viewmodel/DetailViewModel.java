package com.example.email.viewmodel;

import android.app.Application;

import com.example.email.bean.ContentData;
import com.example.email.bean.DetailData;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class DetailViewModel extends AndroidViewModel {
    private MutableLiveData<List<DetailData>> mDetailData;
    private MutableLiveData<List<ContentData>> mContentData;


    public DetailViewModel(@NonNull Application application) {
        super(application);
        mDetailData = new MutableLiveData<>();
        mContentData = new MutableLiveData<>();
    }

    public MutableLiveData<List<DetailData>> getDetailDataList() {
        return mDetailData;
    }

    public MutableLiveData<List<ContentData>> getContentDataList() {
        return mContentData;
    }

    public void requestData() {
        List<DetailData> tabs = new ArrayList<>();
        tabs.add(new DetailData("清醒钟", "1"));
        tabs.add(new DetailData("新鲜事", "2"));
        tabs.add(new DetailData("解压室", "3"));
        tabs.add(new DetailData("下饭菜", "4"));
        tabs.add(new DetailData("微兴趣", "5"));
        tabs.add(new DetailData("小剧场", "6"));
        getDetailDataList().postValue(tabs);
    }

    public void requestContentData(String tabType) {
        List<ContentData> contents = new ArrayList<>();
        final String[] tabDesc = {""};
        getDetailDataList().getValue().forEach(new Consumer<DetailData>() {
            @Override
            public void accept(DetailData detailData) {
                if (detailData.getType().equals(tabType)) {
                    tabDesc[0] = detailData.getTitle();
                }
            }
        });
        String title = tabDesc[0];
        contents.add(new ContentData(tabType, title + " - 习近平举行仪式欢迎洪都拉斯总统访华 同洪都拉斯总统举行会谈", "http://pic1.win4000.com/wallpaper/2018-07-17/5b4da2156f08b.jpg"));
        contents.add(new ContentData(tabType, title + " - 全球连线｜中阿合作论坛第十届企业家大会在沙特举行", "http://pic1.win4000.com/wallpaper/2018-07-17/5b4da21801b49.jpg"));
        contents.add(new ContentData(tabType, title + " - 全球连线", "http://pic1.win4000.com/wallpaper/2018-07-17/5b4da20d335ec.jpg"));
        contents.add(new ContentData(tabType, title + " - 高管在德国被带走企业疑涉及三家 均为光伏组件制造商", "http://pic1.win4000.com/wallpaper/2018-07-17/5b4da2156f08b.jpg"));
        contents.add(new ContentData(tabType, title + " - 村民被十余人入室打砸一度病危 案发近17年3嫌犯落网", "http://pic1.win4000.com/wallpaper/2018-07-17/5b4da219ae316.jpg"));
        contents.add(new ContentData(tabType, title + " - 省纪委监委启动倒查后 12人被点名：多名干部酒后打人", "http://pic1.win4000.com/wallpaper/2018-09-11/5b97aa915cdd6.jpg"));
        getContentDataList().setValue(contents);
    }
}
