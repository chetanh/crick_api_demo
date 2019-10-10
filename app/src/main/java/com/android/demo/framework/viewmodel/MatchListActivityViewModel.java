package com.android.demo.framework.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.demo.framework.models.FeaturedMatchResponseModel;
import com.android.demo.framework.models.OldMatchResponseModel;
import com.android.demo.framework.network.CricketApiRepository;

import javax.inject.Inject;

public class MatchListActivityViewModel extends AndroidViewModel {

    private LiveData<FeaturedMatchResponseModel> featuredMatchResponseModelMutableLiveData = new MutableLiveData<>();
    private LiveData<OldMatchResponseModel> oldMatchResponseModelMutableLiveData = new MutableLiveData<>();

    @Inject
    public MatchListActivityViewModel(@NonNull CricketApiRepository cricketApiRepository, @NonNull Application application) {
        super(application);

        featuredMatchResponseModelMutableLiveData = cricketApiRepository.getFeaturedMatches();
        oldMatchResponseModelMutableLiveData = cricketApiRepository.getOldMatches();

    }

    public LiveData<FeaturedMatchResponseModel> getFeaturedMatchResponseModelMutableLiveData() {
        return featuredMatchResponseModelMutableLiveData;
    }

    public LiveData<OldMatchResponseModel> getOldMatchResponseModelMutableLiveData() {
        return oldMatchResponseModelMutableLiveData;
    }
}