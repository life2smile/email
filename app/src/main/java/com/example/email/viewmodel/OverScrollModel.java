package com.example.email.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OverScrollModel extends AndroidViewModel {
    private MutableLiveData<Float> mOverscrollDx;

    public OverScrollModel(@NonNull Application application) {
        super(application);
        mOverscrollDx = new MutableLiveData(0F);
    }

    public MutableLiveData<Float> getOverscrollDx() {
        return mOverscrollDx;
    }
}