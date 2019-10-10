package com.android.demo.ui.match_detail;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.android.demo.R;
import com.android.demo.databinding.ActivityMatchDetailsBinding;
import com.android.demo.framework.ui.BaseActivity;
import com.android.demo.framework.viewmodel.MatchDetailActivityViewModel;
import com.android.demo.ui.match_list.MatchListActivity;

import javax.inject.Inject;

public class MatchDetailActivity extends BaseActivity {

    private static final String TAG = MatchListActivity.class.getSimpleName();
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    ActivityMatchDetailsBinding activityMatchDetailsBinding;

    public static Bundle createMatchDetailForMatchUniqueId(String uniqueID) {

        Bundle bundle = new Bundle();
        bundle.putString("UNIQUE_ID", uniqueID);
        return bundle;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        activityMatchDetailsBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_match_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //start getting data in background
        final MatchDetailActivityViewModel matchDetailActivityViewModel = ViewModelProviders.of(this,
                viewModelFactory).get(MatchDetailActivityViewModel.class);

        matchDetailActivityViewModel.setUniqueId(getIntent().getStringExtra("UNIQUE_ID"));
        activityMatchDetailsBinding.setIsLoading(true);
        //register observer to update UI
        observeViewModel(matchDetailActivityViewModel);


    }

    private void observeViewModel(MatchDetailActivityViewModel matchDetailActivityViewModel) {
        matchDetailActivityViewModel.getMatchDetailLiveData().observe(this, matchDetail -> {
            if (matchDetail != null) {
                //data binding over here
                activityMatchDetailsBinding.setMatchDetails(matchDetail);
                activityMatchDetailsBinding.setIsLoading(false);

            }
        });
    }
}
