package com.android.demo.framework.injectors;


import com.android.demo.ui.splash.SampleFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Instance provider for fragment
 */
@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract SampleFragment contributeProjectFragment();


}