package com.firebasetestapp.tmdbapitestapp.di.module;

import android.app.Application;

import com.firebasetestapp.tmdbapitestapp.data.local.AppDb;
import com.firebasetestapp.tmdbapitestapp.data.local.MovieDao;

import javax.inject.Singleton;

import androidx.annotation.NonNull;
import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {

    @Singleton
    @Provides
    AppDb provideAppDb(@NonNull Application application) {
        return AppDb.getInstance(application);
    }

    @Singleton
    @Provides
    MovieDao provideMovieDao(@NonNull AppDb appDb) {
        return appDb.getMovieDao();
    }
}
