package com.firebasetestapp.tmdbapitestapp.ui.pagekeyed;

import com.firebasetestapp.tmdbapitestapp.AppExecutors;
import com.firebasetestapp.tmdbapitestapp.data.AppRepository;
import com.firebasetestapp.tmdbapitestapp.data.Resource;
import com.firebasetestapp.tmdbapitestapp.data.datasource.pagekeyed.PageKeyedDataSourceFactory;
import com.firebasetestapp.tmdbapitestapp.data.local.Movie;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import io.reactivex.disposables.CompositeDisposable;

@Singleton
public class PageKeyedViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private CompositeDisposable mCompositeDisposable;
    private PageKeyedDataSourceFactory mPageKeyedDataSourceFactory;
    private AppExecutors mAppExecutors;
    private MutableLiveData<Resource> mStatusLiveData;
    private LiveData<PagedList<Movie>> mMovieLiveData;

    @Inject
    public PageKeyedViewModel(AppRepository appRepository, AppExecutors appExecutors) {
        mAppExecutors = appExecutors;
        mCompositeDisposable = new CompositeDisposable();
        mStatusLiveData = new MutableLiveData<>();
        mPageKeyedDataSourceFactory = new PageKeyedDataSourceFactory(appRepository, mCompositeDisposable, mStatusLiveData);

        initLivePagedData();
    }

    private void initLivePagedData() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(20)
                .build();

        mMovieLiveData = new LivePagedListBuilder<Long, Movie>(mPageKeyedDataSourceFactory, config)
                .setFetchExecutor(mAppExecutors.getDiskIO())
                .build();
    }

    public LiveData<PagedList<Movie>> getMovieLiveData() {
        return mMovieLiveData;
    }

    public LiveData<Resource> getStatusLiveData() {
        return mStatusLiveData;
    }
}
