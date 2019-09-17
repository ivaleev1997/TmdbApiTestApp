package com.firebasetestapp.tmdbapitestapp.di.module;

import com.firebasetestapp.tmdbapitestapp.AppExecutors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    AppExecutors provideAppExecutors() {
        return new AppExecutors();
    }
}
