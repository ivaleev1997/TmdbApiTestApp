package com.firebasetestapp.tmdbapitestapp.data.datasource.pagekeyed;

import com.firebasetestapp.tmdbapitestapp.data.AppRepository;
import com.firebasetestapp.tmdbapitestapp.data.Resource;
import com.firebasetestapp.tmdbapitestapp.data.local.Movie;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;
import io.reactivex.disposables.CompositeDisposable;

import static com.firebasetestapp.tmdbapitestapp.AppConstants.PAGEKEYED_INITIAL_KEY;

public class PageKeyedMovieDataSource extends PageKeyedDataSource<Long, Movie> {

    private AppRepository mAppRepository;
    private CompositeDisposable mCompositeDisposable;
    private MutableLiveData<Resource> mStatusLiveData;

    public PageKeyedMovieDataSource(AppRepository appRepository, CompositeDisposable compositeDisposable, MutableLiveData<Resource> resourceMutableLiveData) {
        mAppRepository = appRepository;
        mCompositeDisposable = compositeDisposable;
        mStatusLiveData = resourceMutableLiveData;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, Movie> callback) {
        mStatusLiveData.postValue(Resource.loading(null));
        mCompositeDisposable.add(
                mAppRepository.getObservableMoviesByPage(PAGEKEYED_INITIAL_KEY)
                        .subscribe(movieMovieApiResponse -> {
                            if (movieMovieApiResponse != null) {
                                if (movieMovieApiResponse.getResults() != null) {
                                    if (!movieMovieApiResponse.getResults().isEmpty()) {
                                        mStatusLiveData.postValue(Resource.success(null));
                                        callback.onResult(movieMovieApiResponse.getResults(),null, movieMovieApiResponse.getPage() + 1);
                                    }
                                } else {
                                    mStatusLiveData.postValue(Resource.error("loadInitial - Results is null", null));
                                    loadInitial(params, callback);
                                }
                            } else {
                                mStatusLiveData.postValue(Resource.error("loadInitial - Api response is null", null));
                                loadInitial(params, callback);
                            }
                        }));
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Movie> callback) {
        mStatusLiveData.postValue(Resource.loading(null));
        mCompositeDisposable.add(mAppRepository.getObservableMoviesByPage(params.key)
                .subscribe(movieMovieApiResponse -> {
                    if (movieMovieApiResponse != null) {
                        if (movieMovieApiResponse.getResults() != null) {
                            if (!movieMovieApiResponse.getResults().isEmpty()) {
                                mStatusLiveData.postValue(Resource.success(null));
                                callback.onResult(movieMovieApiResponse.getResults(), movieMovieApiResponse.getPage() - 1 > 0 ? null : movieMovieApiResponse.getPage() - 1);
                            }
                        } else {
                            mStatusLiveData.postValue(Resource.error("loadBefore - Results is null", null));
                            loadBefore(params, callback);
                        }
                    } else {
                        mStatusLiveData.postValue(Resource.error("loadBefore - Api response is null", null));
                        loadBefore(params, callback);
                    }
                }));
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Movie> callback) {
        mStatusLiveData.postValue(Resource.loading(null));
        mCompositeDisposable.add(
                mAppRepository.getObservableMoviesByPage(params.key)
                        .subscribe(movieMovieApiResponse -> {
                                    if (movieMovieApiResponse != null) {
                                        if (movieMovieApiResponse.getResults() != null) {
                                            if (!movieMovieApiResponse.getResults().isEmpty()) {
                                                mStatusLiveData.postValue(Resource.success(null));
                                                callback.onResult(movieMovieApiResponse.getResults(), movieMovieApiResponse.getPage() + 1 > movieMovieApiResponse.getTotalPages() ? null : movieMovieApiResponse.getPage() + 1);
                                            }
                                        } else {
                                            mStatusLiveData.postValue(Resource.error("loadAfter - Results is null", null));
                                            loadAfter(params, callback);
                                        }
                                    } else {
                                        mStatusLiveData.postValue(Resource.error("loadAfter - Api response is null", null));
                                        loadAfter(params, callback);
                                    }
                                }
                        ));
    }
}
