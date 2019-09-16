package com.firebasetestapp.tmdbapitestapp.di.module;

import com.firebasetestapp.tmdbapitestapp.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract MainActivity contributeMainActivity();

}
