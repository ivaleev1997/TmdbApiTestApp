package com.firebasetestapp.tmdbapitestapp.data;


import com.firebasetestapp.tmdbapitestapp.data.remote.MovieApiResponse;

import java.util.List;

import androidx.annotation.MainThread;
import androidx.annotation.WorkerThread;
import io.reactivex.Observable;

public abstract class NetworkBoundResource<ResultType, RequestType> {

    Observable result;

    public NetworkBoundResource() {
        result = Observable.empty();
        //TODO
    }

    @WorkerThread
    protected abstract void saveCallResult(RequestType requestType);

    @MainThread
    protected abstract Boolean shouldFetch();

    @MainThread
    protected abstract Observable<List<ResultType>> loadFromDb();

    @MainThread
    protected abstract Observable<MovieApiResponse<ResultType>> createCall();
}
