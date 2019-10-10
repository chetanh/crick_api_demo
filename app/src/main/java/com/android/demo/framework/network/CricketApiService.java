package com.android.demo.framework.network;


import com.android.demo.framework.models.FeaturedMatchResponseModel;
import com.android.demo.framework.models.MatchDetail;
import com.android.demo.framework.models.OldMatchResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CricketApiService {

    @GET("matches")
    Call<FeaturedMatchResponseModel> getFeaturedMatches(@Query("apikey") String apiKey);

    @GET("cricket")
    Call<OldMatchResponseModel> getOldMatches(@Query("apikey") String apiKey);

    @GET("cricketScore")
    Call<MatchDetail> getMatchDetails(@Query("apikey") String apiKey, @Query("unique_id") String uniqueId);
}