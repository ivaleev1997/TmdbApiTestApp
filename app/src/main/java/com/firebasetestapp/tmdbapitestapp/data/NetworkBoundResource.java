package com.firebasetestapp.tmdbapitestapp.data;


import androidx.annotation.MainThread;
import androidx.annotation.WorkerThread;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

public abstract class NetworkBoundResource<ResultType, RequestType> {

    Observable<Resource<ResultType>> result;

    public NetworkBoundResource() {
        Observable<Resource<ResultType>> source;

        source = createCall()
                .subscribeOn(Schedulers.io())
                .doOnNext(movieApiResponseResource -> saveCallResult(processResponse(movieApiResponseResource)))
                .flatMap(movieApiResponseResource -> loadFromDb().toObservable().map(Resource::success))
                .doOnError(throwable -> fetchFailed())
                .onErrorResumeNext(throwable -> {
                    return loadFromDb().toObservable().map(result -> Resource.error(throwable.getMessage(), result));
                })
                .observeOn(AndroidSchedulers.mainThread());

        result = Observable.concat(loadFromDb().toObservable().map(Resource::success).take(1), source);
    }

    public Observable<Resource<ResultType>> getObservable() {
        return result;
    }

    @WorkerThread
    private RequestType processResponse(Resource<RequestType> responseResource) {
        return responseResource.data;
    }

    protected abstract void fetchFailed();

    @WorkerThread
    protected abstract void saveCallResult(RequestType item);

    @MainThread
    protected abstract Boolean shouldFetch();

    @MainThread
    protected abstract Flowable<ResultType> loadFromDb();

    @MainThread
    @NonNull
    protected abstract Observable<Resource<RequestType>> createCall();
}
