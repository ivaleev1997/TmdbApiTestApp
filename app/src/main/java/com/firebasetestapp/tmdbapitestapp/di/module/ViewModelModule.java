package com.firebasetestapp.tmdbapitestapp.di.module;

import com.firebasetestapp.tmdbapitestapp.MainActivityViewModel;
import com.firebasetestapp.tmdbapitestapp.di.ViewModuleKey;
import com.firebasetestapp.tmdbapitestapp.ui.AppViewModelFactory;
import com.firebasetestapp.tmdbapitestapp.ui.movierecycler.MovieRecyclerViewModel;
import com.firebasetestapp.tmdbapitestapp.ui.pagekeyed.PageKeyedViewModel;
import com.firebasetestapp.tmdbapitestapp.ui.positional.PositionalPagedViewModel;

import androidx.lifecycle.ViewModel;
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
    abstract ViewModel bindMovieRecyclerViewModel(MovieRecyclerViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModuleKey(MainActivityViewModel.class)
    abstract ViewModel bindMainActivityViewModel(MainActivityViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModuleKey(PositionalPagedViewModel.class)
    abstract ViewModel bindPositionalPagedViewModel(PositionalPagedViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModuleKey(PageKeyedViewModel.class)
    abstract ViewModel bindPageKeyedViewModel(PageKeyedViewModel viewModel);

}
