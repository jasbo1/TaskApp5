package com.taskapp.ui.anime;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AnimeViewModels extends ViewModel {

    private MutableLiveData<String> mText;

    public AnimeViewModels() {
        mText = new MutableLiveData<>();
        mText.setValue("This is anime fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}