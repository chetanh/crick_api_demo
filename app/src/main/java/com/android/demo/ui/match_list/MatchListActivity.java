package com.android.demo.ui.match_list;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.android.demo.R;
import com.android.demo.databinding.ActivityMatchListBinding;
import com.android.demo.framework.models.Match;
import com.android.demo.framework.ui.BaseActivity;
import com.android.demo.framework.viewmodel.MatchListActivityViewModel;
import com.android.demo.ui.match_detail.MatchDetailActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MatchListActivity extends BaseActivity {

    private static final String TAG = MatchListActivity.class.getSimpleName();
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private MatchRecyclerViewAdapter liveMatchRecyclerViewAdapter;
    private MatchRecyclerViewAdapter upcomingMatchRecyclerViewAdapter;
    private MatchRecyclerViewAdapter recentMatchRecyclerViewAdapter;
    private OldMatchRecyclerViewAdapter oldMatchRecyclerViewAdapter;
    private ActivityMatchListBinding activityMatchListBinding;
    private MatchRecyclerViewAdapter.MatchItemClickListener matchItemClickListener = match -> {
        Intent intent = new Intent(MatchListActivity.this, MatchDetailActivity.class);
        intent.putExtras(MatchDetailActivity.createMatchDetailForMatchUniqueId(match.getUniqueId()));
        startActivity(intent);
    };
    private OldMatchRecyclerViewAdapter.OldMatchItemClickListener oldMatchItemClickListener = oldMatch -> {
        Intent intent = new Intent(MatchListActivity.this, MatchDetailActivity.class);
        intent.putExtras(MatchDetailActivity.createMatchDetailForMatchUniqueId(oldMatch.getUniqueId()));
        startActivity(intent);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        activityMatchListBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_match_list);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        liveMatchRecyclerViewAdapter = new MatchRecyclerViewAdapter(matchItemClickListener);
        upcomingMatchRecyclerViewAdapter = new MatchRecyclerViewAdapter(matchItemClickListener);
        recentMatchRecyclerViewAdapter = new MatchRecyclerViewAdapter(matchItemClickListener);
        oldMatchRecyclerViewAdapter = new OldMatchRecyclerViewAdapter(oldMatchItemClickListener);

        activityMatchListBinding.liveMatches.matchRecyclerView.setAdapter(liveMatchRecyclerViewAdapter);
        activityMatchListBinding.upcomingMatches.matchRecyclerView.setAdapter(upcomingMatchRecyclerViewAdapter);
        activityMatchListBinding.recentMatches.matchRecyclerView.setAdapter(recentMatchRecyclerViewAdapter);
        activityMatchListBinding.oldMatches.matchRecyclerView.setAdapter(oldMatchRecyclerViewAdapter);

        activityMatchListBinding.liveMatches.matchesListTitle.setText(R.string.live_match_title);
        activityMatchListBinding.upcomingMatches.matchesListTitle.setText(R.string.upcoming_match_title);
        activityMatchListBinding.recentMatches.matchesListTitle.setText(R.string.recent_match_title);
        activityMatchListBinding.oldMatches.matchesListTitle.setText(R.string.old_match_title);

        activityMatchListBinding.liveMatches.setIsLoading(true);
        activityMatchListBinding.upcomingMatches.setIsLoading(true);
        activityMatchListBinding.recentMatches.setIsLoading(true);
        activityMatchListBinding.oldMatches.setIsLoading(true);

        //start getting data in background
        final MatchListActivityViewModel matchListActivityViewModel = ViewModelProviders.of(this,
                viewModelFactory).get(MatchListActivityViewModel.class);

        //register observer to update UI
        observeViewModel(matchListActivityViewModel);

    }

    private void observeViewModel(MatchListActivityViewModel matchListActivityViewModel) {

        /**
         *
         * TODO:
         * If we get live upcoming and recent on the basis of request flag this logic
         * will get changed and can have independent fragments for each of scroll view.
         *
         * This fragment can be configured with recycler view vertical
         * and more button can be implemented.
         *
         *  Update the list when the data changes - live, upcoming & recent matches
         */
        matchListActivityViewModel.getFeaturedMatchResponseModelMutableLiveData().observe(
                this, featuredMatchResponseModel -> {

                    if (featuredMatchResponseModel != null) {
//                    binding.setIsLoading(false);

                        List<Match> featureMatchMaster = featuredMatchResponseModel.getMatches();
                        List<Match> upcomingMatchList = new ArrayList<>();
                        List<Match> liveMatchList = new ArrayList<>();
                        List<Match> recentMatchList = new ArrayList<>();

                        for (Match match : featureMatchMaster) {
                            if (!match.isMatchStarted()) {
                                //schedule match
                                match.setStat(MatchListActivity.this.getString(R.string.match_schedule_label));
                                upcomingMatchList.add(match);
                            } else if (TextUtils.isEmpty(match.getWinnerTeam())) {
                                //live match
                                match.setStat(MatchListActivity.this.getString(R.string.match_progress_label));
                                liveMatchList.add(match);

                            } else {
                                //recent match
                                match.setStat(MatchListActivity.this.getString(R.string.recent_match_label) + match.getWinnerTeam());
                                recentMatchList.add(match);
                            }
                        }

                        /*
                         * TODO:
                         * Here more option can be set instead of showing long list of matches and
                         * navigate user to next screen.
                         */

                        liveMatchRecyclerViewAdapter.setMatchList(liveMatchList);
                        upcomingMatchRecyclerViewAdapter.setMatchList(upcomingMatchList);
                        recentMatchRecyclerViewAdapter.setMatchList(recentMatchList);

                        activityMatchListBinding.liveMatches.setIsLoading(false);
                        activityMatchListBinding.upcomingMatches.setIsLoading(false);
                        activityMatchListBinding.recentMatches.setIsLoading(false);
                        activityMatchListBinding.oldMatches.setIsLoading(false);

                    }
                });

        matchListActivityViewModel.getOldMatchResponseModelMutableLiveData().observe(this, oldMatchResponseModel -> {

            if (oldMatchResponseModel != null) {
                oldMatchRecyclerViewAdapter.setOldMatchList(oldMatchResponseModel.getOldMatches());
            }

        });
    }
}