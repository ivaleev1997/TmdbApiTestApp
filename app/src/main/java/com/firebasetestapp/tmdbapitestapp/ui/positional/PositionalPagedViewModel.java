package com.firebasetestapp.tmdbapitestapp.ui.positional;

import com.firebasetestapp.tmdbapitestapp.AppExecutors;
import com.firebasetestapp.tmdbapitestapp.data.AppRepository;
import com.firebasetestapp.tmdbapitestapp.data.datasource.PositionMovieDataSourceFactory;
import com.firebasetestapp.tmdbapitestapp.data.local.Movie;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

public class PositionalPagedViewModel extends ViewModel {

    private AppRepository mAppRepository;
    private AppExecutors mAppExecutors;
    private PositionMovieDataSourceFactory mPositionMovieDataSourceFactory;
    private LiveData<PagedList<Movie>> mPagedListLiveData;
    //private MutableLiveData<>

    @Inject
    public PositionalPagedViewModel(AppRepository repository, AppExecutors appExecutors) {
        mAppRepository = repository;
        mAppExecutors = appExecutors;

        initPagedData();
    }

    private void initPagedData() {
        mPositionMovieDataSourceFactory = new PositionMovieDataSourceFactory(mAppRepository);
        PagedList.Config config = new PagedList.Config.Builder().setEnablePlaceholders(false).setPageSize(10).build();
        mPagedListLiveData = new LivePagedListBuilder<Integer, Movie>(mPositionMovieDataSourceFactory, config)
                .setFetchExecutor(mAppExecutors.getDiskIO())
                .build();
    }

    public LiveData<PagedList<Movie>> getPagedListLiveData() {
        return mPagedListLiveData;
    }
}
