package com.ds.carpooling.fragment.homeUser;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeUserViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeUserViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}