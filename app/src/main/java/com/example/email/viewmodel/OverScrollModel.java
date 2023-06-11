package com.example.email.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OverScrollModel extends AndroidViewModel {
    private MutableLiveData<Float> mPositionOffset;
    private MutableLiveData<Integer> mPositionOffsetPixels;


    public OverScrollModel(@NonNull Application application) {
        super(application);
        mPositionOffset = new MutableLiveData(0F);
        mPositionOffsetPixels = new MutableLiveData<>(0);
    }

    public MutableLiveData<Float> getPositionOffset() {
        return mPositionOffset;
    }

    public MutableLiveData<Integer> getPositionOffsetPixels() {
        return mPositionOffsetPixels;
    }
}