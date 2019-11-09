package com.firebasetestapp.tmdbapitestapp.di.module;

import com.firebasetestapp.tmdbapitestapp.ui.movierecycler.MovieRecyclerFragment;
import com.firebasetestapp.tmdbapitestapp.ui.pagekeyed.PageKeyedFragment;
import com.firebasetestapp.tmdbapitestapp.ui.positional.PositionalPagedFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract MovieRecyclerFragment contributeMovieRecyclerFragment();

    @ContributesAndroidInjector
    abstract PositionalPagedFragment contributePositionalPagedFragment();

    @ContributesAndroidInjector
    abstract PageKeyedFragment contributePageKeyedFragment();
}
