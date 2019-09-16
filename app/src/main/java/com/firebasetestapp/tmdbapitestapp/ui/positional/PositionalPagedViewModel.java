package com.firebasetestapp.tmdbapitestapp.ui.positional;

import com.firebasetestapp.tmdbapitestapp.data.AppRepository;
import com.firebasetestapp.tmdbapitestapp.data.datasource.PositionMovieDataSourceFactory;
import com.firebasetestapp.tmdbapitestapp.data.local.Movie;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

public class PositionalPagedViewModel extends ViewModel {
    // TODO: Implement the ViewModel


    private AppRepository mAppRepository;
    private PositionMovieDataSourceFactory mPositionMovieDataSourceFactory;
    private LiveData<PagedList<Movie>> mPagedListLiveData;


    @Inject
    public PositionalPagedViewModel(AppRepository repository) {
        mAppRepository = repository;

        mPositionMovieDataSourceFactory = new PositionMovieDataSourceFactory(mAppRepository);

        PagedList.Config config = new PagedList.Config.Builder().setEnablePlaceholders(false).setPageSize(10).build();
    }


}
