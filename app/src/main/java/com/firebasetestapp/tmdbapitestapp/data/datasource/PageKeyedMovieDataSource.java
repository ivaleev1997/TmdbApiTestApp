package com.firebasetestapp.tmdbapitestapp.data.datasource;

import com.firebasetestapp.tmdbapitestapp.data.AppRepository;
import com.firebasetestapp.tmdbapitestapp.data.local.Movie;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import io.reactivex.disposables.CompositeDisposable;

public class PageKeyedMovieDataSource extends PageKeyedDataSource<Long, Movie> {

    private AppRepository mAppRepository;
    private CompositeDisposable mCompositeDisposable;

    public PageKeyedMovieDataSource(AppRepository appRepository, CompositeDisposable compositeDisposable) {
        mAppRepository = appRepository;
        mCompositeDisposable = compositeDisposable;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, Movie> callback) {

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Movie> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Movie> callback) {

    }
}
