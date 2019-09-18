package com.firebasetestapp.tmdbapitestapp;

import com.firebasetestapp.tmdbapitestapp.data.AppRepository;
import com.firebasetestapp.tmdbapitestapp.data.Status;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;

@Singleton
public class MainActivityViewModel extends ViewModel {

    private static final int MIN_MOVIES_COUNT = 100;
    private AppRepository mAppRepository;
    private AppExecutors mAppExecutors;
    private CompositeDisposable mCompositeDisposable;
    private MutableLiveData<Status> mStatusLiveData;

    @Inject
    public MainActivityViewModel(AppRepository appRepository, AppExecutors appExecutors) {
        mAppRepository = appRepository;
        mAppExecutors = appExecutors;
        mCompositeDisposable = new CompositeDisposable();
        mStatusLiveData = new MutableLiveData<>();

        mAppExecutors.getDiskIO().execute(() -> {
            if (!shouldInitDb()) mCompositeDisposable.add(mAppRepository.initialLoadDb().subscribe(() -> mStatusLiveData.postValue(Status.SUCCESS), throwable -> mStatusLiveData.postValue(Status.ERROR)));
        });

    }

    @WorkerThread
    private boolean shouldInitDb() {
        return mAppRepository.mMovieDao.getMovies().size() >= MIN_MOVIES_COUNT;
    }

    public LiveData<Status> getStatusLiveData() {
        if (mStatusLiveData == null)
            mStatusLiveData = new MutableLiveData<>();

        return mStatusLiveData;
    }

}
