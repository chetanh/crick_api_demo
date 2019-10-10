package com.android.demo.framework.network;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.demo.BuildConfig;
import com.android.demo.framework.models.FeaturedMatchResponseModel;
import com.android.demo.framework.models.MatchDetail;
import com.android.demo.framework.models.OldMatchResponseModel;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class CricketApiRepository {

    private CricketApiService cricketApiService;

    @Inject
    public CricketApiRepository(CricketApiService cricketApiService) {
        this.cricketApiService = cricketApiService;
    }


    public LiveData<FeaturedMatchResponseModel> getFeaturedMatches() {
        final MutableLiveData<FeaturedMatchResponseModel> featuredMatchResponseModelMutableLiveData =
                new MutableLiveData<>();

        cricketApiService.getFeaturedMatches(BuildConfig.CRICKET_API_KEY).enqueue(new Callback<FeaturedMatchResponseModel>() {
            @Override
            public void onFailure(Call<FeaturedMatchResponseModel> call, Throwable t) {
                featuredMatchResponseModelMutableLiveData.setValue(null);
            }

            @Override
            public void onResponse(Call<FeaturedMatchResponseModel> call, Response<FeaturedMatchResponseModel> response) {
                featuredMatchResponseModelMutableLiveData.setValue(response.body());

            }
        });

        return featuredMatchResponseModelMutableLiveData;

    }

    public LiveData<OldMatchResponseModel> getOldMatches() {
        final MutableLiveData<OldMatchResponseModel> oldMatchResponseModelMutableLiveData =
                new MutableLiveData<>();

        cricketApiService.getOldMatches(BuildConfig.CRICKET_API_KEY).enqueue(new Callback<OldMatchResponseModel>() {
            @Override
            public void onFailure(Call<OldMatchResponseModel> call, Throwable t) {
                oldMatchResponseModelMutableLiveData.setValue(null);
            }

            @Override
            public void onResponse(Call<OldMatchResponseModel> call, Response<OldMatchResponseModel> response) {
                oldMatchResponseModelMutableLiveData.setValue(response.body());

            }
        });

        return oldMatchResponseModelMutableLiveData;

    }


    public LiveData<MatchDetail> getMatchDetails(String uniqueId) {
        final MutableLiveData<MatchDetail> matchDetailMutableLiveData =
                new MutableLiveData<>();

        cricketApiService.getMatchDetails(BuildConfig.CRICKET_API_KEY,
                uniqueId).enqueue(new Callback<MatchDetail>() {
            @Override
            public void onFailure(Call<MatchDetail> call, Throwable t) {
                matchDetailMutableLiveData.setValue(null);
            }

            @Override
            public void onResponse(Call<MatchDetail> call, Response<MatchDetail> response) {
                matchDetailMutableLiveData.setValue(response.body());

            }
        });

        return matchDetailMutableLiveData;

    }
}