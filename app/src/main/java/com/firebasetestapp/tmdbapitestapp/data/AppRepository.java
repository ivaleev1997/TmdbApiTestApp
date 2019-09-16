package com.firebasetestapp.tmdbapitestapp.data;

import android.util.Log;

import com.firebasetestapp.tmdbapitestapp.AppConstants;
import com.firebasetestapp.tmdbapitestapp.data.local.AppDb;
import com.firebasetestapp.tmdbapitestapp.data.local.Movie;
import com.firebasetestapp.tmdbapitestapp.data.local.MovieDao;
import com.firebasetestapp.tmdbapitestapp.data.remote.MovieApiService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class AppRepository {

    public AppDb mAppDb;
    public MovieDao mMovieDao;
    public MovieApiService mMovieApiService;

    @Inject
    public AppRepository(AppDb appDb, MovieApiService movieApiService) {
        mAppDb = appDb;
        mMovieDao = appDb.getMovieDao();
        mMovieApiService = movieApiService;
    }

    public Completable initialLoadDb() {
        return Completable.fromObservable(
                mMovieApiService.fetchPopularMovies()
                        .subscribeOn(Schedulers.io())
                        .flatMap(movieApiService -> {
                            Log.d(AppConstants.TAG, "Current flatMap thread: " + Thread.currentThread());
                            return Observable.just(movieApiService.getResults());
                        })
                        .doOnNext(movies -> {
                            //save data in DB
                            mMovieDao.insertListMovies(movies);
                            Log.d(AppConstants.TAG, "Current doOnNext thread: " + Thread.currentThread());
                        }
        ));
    }

    public List<Movie> getListMovies(int startPos, int size) {
        List<Movie> result = new ArrayList<>();
        List<Movie> movies = mMovieDao.getMovies();

        if (startPos + size - 1 < movies.size())
            result = movies.subList(startPos, startPos + size - 1);
        else if (movies.get(startPos) != null)
            result = movies.subList(startPos, movies.size() - 1);
        //movies.subList(startPos, );
        /*for (Movie m : movies) {
           if (movies.indexOf(m) >= startPos && size >= 0) {
               result.add(m);
               size--;
           }
        }*/

        return result;
    }
}
