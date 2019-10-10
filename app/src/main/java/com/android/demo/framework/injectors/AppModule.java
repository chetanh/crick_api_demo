package com.android.demo.framework.injectors;

import androidx.lifecycle.ViewModelProvider;

import com.android.demo.framework.network.CricketApiService;
import com.android.demo.framework.network.retrofit_okhttp.WebServiceGenerator;
import com.android.demo.framework.viewmodel.CricketViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * This class provides singleton services for all modules like ViewModel factory,
 * retrofit service instances.
 * <p>
 * We can register mode modules as per future requirements like probably SQLite module.
 */
@Module(subcomponents = ViewModelSubComponent.class)
class AppModule {

    @Singleton
    @Provides
    CricketApiService provideCricketService() {
        return WebServiceGenerator.createService(CricketApiService.class);
    }

    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(
            ViewModelSubComponent.Builder viewModelSubComponent) {

        return new CricketViewModelFactory(viewModelSubComponent.build());
    }
}