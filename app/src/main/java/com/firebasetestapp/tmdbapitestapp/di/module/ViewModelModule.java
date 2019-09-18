package com.firebasetestapp.tmdbapitestapp.di.module;

import com.firebasetestapp.tmdbapitestapp.MainActivityViewModel;
import com.firebasetestapp.tmdbapitestapp.ui.AppViewModelFactory;
import com.firebasetestapp.tmdbapitestapp.ui.movierecycler.MovieRecyclerViewModel;
import com.firebasetestapp.tmdbapitestapp.di.ViewModuleKey;

import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindAppViewModelFactory(AppViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModuleKey(MovieRecyclerViewModel.class)
    abstract MovieRecyclerViewModel bindMovieRecyclerViewModel(MovieRecyclerViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModuleKey(MainActivityViewModel.class)
    abstract MainActivityViewModel bindMainActivityViewModel(MainActivityViewModel viewModel);
}
