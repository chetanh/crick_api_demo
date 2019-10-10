package com.android.demo.framework.ui;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Base activity for all other activities and common code can be written over here.
 */
@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    /**
     * Actually fragment is not used in this demo,
     * but we can use this as common code for all out activities.
     */
    protected FragmentManager _fragmentManager;
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _fragmentManager = getSupportFragmentManager();
    }

    /**
     * Start new fragment
     *
     * @param fragment = Target Fragment
     */
    protected void startFragment(int resId, Fragment fragment) {

        FragmentTransaction fragmentTransaction = _fragmentManager.beginTransaction();
        fragmentTransaction.replace(resId, fragment, fragment.getClass().getName()).commit();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}