package com.android.demo.framework.injectors;

import com.android.demo.framework.viewmodel.MatchDetailActivityViewModel;
import com.android.demo.framework.viewmodel.MatchListActivityViewModel;

import dagger.Subcomponent;

/**
 * A sub component to create ViewModels. It is called by the
 * CricketViewModelFactory
 */
@Subcomponent
public interface ViewModelSubComponent {

    MatchListActivityViewModel matchListActivityViewModel();

    MatchDetailActivityViewModel matchDetailActivityViewModel();

    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }
}