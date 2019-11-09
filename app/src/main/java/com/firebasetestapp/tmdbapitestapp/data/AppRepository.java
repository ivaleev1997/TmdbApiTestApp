package com.firebasetestapp.tmdbapitestapp.data;

import com.firebasetestapp.tmdbapitestapp.data.local.AppDb;
import com.firebasetestapp.tmdbapitestapp.data.local.Movie;
import com.firebasetestapp.tmdbapitestapp.data.local.MovieDao;
import com.firebasetestapp.tmdbapitestapp.data.remote.MovieApiResponse;
import com.firebasetestapp.tmdbapitestapp.data.remote.MovieApiService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
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
        //Observable merged = Observable.merge();
        return Completable.fromObservable(
                Observable.merge(
                        mMovieApiService.fetchPopularMoviesByPage(1),
                        mMovieApiService.fetchPopularMoviesByPage(2),
                        mMovieApiService.fetchPopularMoviesByPage(3),
                        mMovieApiService.fetchPopularMoviesByPage(4))
                        .subscribeOn(Schedulers.io())
                        .doOnNext(movieApiResponse -> {
                            if (movieApiResponse != null) {
                                List<Movie> movies = movieApiResponse.getResults();
                                mMovieDao.insertListMovies(movies);
                            }
                            System.out.println("success");
                        }));
    }

    public List<Movie> getListMovies(int startPos, int size) {
        List<Movie> result = new ArrayList<>();
        try {
            List<Movie> movies = mMovieDao.getMovies();

            if (startPos + size - 1 < movies.size())
                result = movies.subList(startPos, startPos + size);
            else if (startPos <= movies.size() - 1)
                result = movies.subList(startPos, movies.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //movies.subList(startPos, );
        /*for (Movie m : movies) {
           if (movies.indexOf(m) >= startPos && size >= 0) {
               result.add(m);
               size--;
           }
        }*/

        return result;
    }

    public LiveData<List<Movie>> getMoviesFromDb() {
        return mMovieDao.getLiveDataListMovies();
    }

    public Observable<MovieApiResponse<Movie>> getObservableMoviesByPage(Long page) {
        return mMovieApiService.fetchPopularMoviesByPage(page)
                .subscribeOn(Schedulers.io());
    }
}
