package com.firebasetestapp.tmdbapitestapp.di.module;

import com.firebasetestapp.tmdbapitestapp.ui.movierecycler.MovieRecyclerFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract MovieRecyclerFragment contributeMovieRecyclerFragment();
}
