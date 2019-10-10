package com.android.demo.framework.injectors;

import com.android.demo.ui.match_detail.MatchDetailActivity;
import com.android.demo.ui.match_list.MatchListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Instance provider for Activity
 */
@Module
public abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract MatchListActivity contributeMatchListActivity();

    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract MatchDetailActivity contributeMatchDetailsActivity();
}