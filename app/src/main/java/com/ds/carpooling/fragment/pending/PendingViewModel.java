package com.ds.carpooling.fragment.pending;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PendingViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PendingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}