package com.firebasetestapp.tmdbapitestapp.data.datasource.pagekeyed;

import com.firebasetestapp.tmdbapitestapp.data.AppRepository;
import com.firebasetestapp.tmdbapitestapp.data.Resource;
import com.firebasetestapp.tmdbapitestapp.data.local.Movie;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;
import io.reactivex.disposables.CompositeDisposable;


public class PageKeyedDataSourceFactory extends PageKeyedDataSource.Factory<Long, Movie> {

    private AppRepository mAppRepository;
    private CompositeDisposable mDisposable;
    private MutableLiveData<Resource> mResourceMutableLiveData;

    public PageKeyedDataSourceFactory(AppRepository appRepository, CompositeDisposable disposable, MutableLiveData<Resource> resourceMutableLiveData) {
        mResourceMutableLiveData = resourceMutableLiveData;
        mAppRepository = appRepository;
        mDisposable = disposable;
    }

    @Override
    public DataSource<Long, Movie> create() {
        mDisposable.clear();

        return new PageKeyedMovieDataSource(mAppRepository, mDisposable, mResourceMutableLiveData);
    }
}
