package com.android.demo.framework.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.android.demo.framework.models.MatchDetail;
import com.android.demo.framework.network.CricketApiRepository;

import javax.inject.Inject;

public class MatchDetailActivityViewModel extends AndroidViewModel {

    private final LiveData<MatchDetail> matchDetailLiveData;
    private final MutableLiveData<String> uniqueId;


    @Inject
    public MatchDetailActivityViewModel(@NonNull CricketApiRepository cricketApiRepository,
                                        @NonNull Application application) {
        super(application);
        this.uniqueId = new MutableLiveData<>();

        matchDetailLiveData = Transformations.switchMap(uniqueId,
                input -> cricketApiRepository.getMatchDetails(uniqueId.getValue()));

    }

    public LiveData<MatchDetail> getMatchDetailLiveData() {
        return matchDetailLiveData;
    }

    public void setUniqueId(@NonNull String uniqueId) {
        this.uniqueId.setValue(uniqueId);
    }

}