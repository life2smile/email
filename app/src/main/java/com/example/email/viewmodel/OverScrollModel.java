package com.example.email.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OverScrollModel extends AndroidViewModel implements DefaultLifecycleObserver {
    private MutableLiveData<Float> mPositionOffset;
    private MutableLiveData<Integer> mPositionOffsetPixels;
    private MutableLiveData<String> mLifeCycleEvent;


    public OverScrollModel(@NonNull Application application) {
        super(application);
        mPositionOffset = new MutableLiveData(0F);
        mPositionOffsetPixels = new MutableLiveData<>(0);
        mLifeCycleEvent = new MutableLiveData<>();
    }

    public MutableLiveData<Float> getPositionOffset() {
        return mPositionOffset;
    }

    public MutableLiveData<Integer> getPositionOffsetPixels() {
        return mPositionOffsetPixels;
    }

    public MutableLiveData<String> getLifeCycleEvent() {
        return mLifeCycleEvent;
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        mLifeCycleEvent.setValue("onResume");
    }
}