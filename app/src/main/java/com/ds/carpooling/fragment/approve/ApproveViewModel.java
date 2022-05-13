package com.ds.carpooling.fragment.approve;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ApproveViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ApproveViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}