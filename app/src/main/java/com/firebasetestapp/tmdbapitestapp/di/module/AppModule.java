package com.firebasetestapp.tmdbapitestapp.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.firebasetestapp.tmdbapitestapp.AppConstants;
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

    @Provides
    @Singleton
    SharedPreferences provideSharedPrefs(Application application) {
        return application.getSharedPreferences(AppConstants.SHARED_PREFS, Context.MODE_PRIVATE);
    }
}
