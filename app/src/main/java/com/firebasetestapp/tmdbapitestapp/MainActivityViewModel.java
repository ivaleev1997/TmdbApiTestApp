package com.firebasetestapp.tmdbapitestapp;

import android.content.SharedPreferences;

import com.firebasetestapp.tmdbapitestapp.data.AppRepository;
import com.firebasetestapp.tmdbapitestapp.data.Status;
import com.firebasetestapp.tmdbapitestapp.utils.RateLimiter;

import java.util.concurrent.TimeUnit;

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
    private static final int UPDATE_TIMEOUT_INTERVAL = 40;
    private static final TimeUnit INTERVAL_UNITS = TimeUnit.MINUTES;
    private AppRepository mAppRepository;
    private AppExecutors mAppExecutors;
    private CompositeDisposable mCompositeDisposable;
    private MutableLiveData<Status> mStatusLiveData;
    private SharedPreferences mSharedPreferences;
    private RateLimiter mRateLimiter;

    @Inject
    public MainActivityViewModel(AppRepository appRepository, AppExecutors appExecutors, SharedPreferences sharedPreferences) {
        mAppRepository = appRepository;
        mAppExecutors = appExecutors;
        mSharedPreferences = sharedPreferences;
        mCompositeDisposable = new CompositeDisposable();
        mStatusLiveData = new MutableLiveData<>();
        mRateLimiter = new RateLimiter(UPDATE_TIMEOUT_INTERVAL, INTERVAL_UNITS, mSharedPreferences);

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

    public void checkForUpdate() {
        //TODO
    }
}
