package com.firebasetestapp.tmdbapitestapp.data.datasource.position;

import com.firebasetestapp.tmdbapitestapp.data.AppRepository;
import com.firebasetestapp.tmdbapitestapp.data.local.Movie;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

public class PositionMovieDataSource extends PositionalDataSource<Movie> {

    private AppRepository mAppRepository;

    public PositionMovieDataSource(AppRepository appRepository) {
        mAppRepository = appRepository;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<Movie> callback) {
        List<Movie> movies = mAppRepository.getListMovies(params.requestedStartPosition, params.requestedLoadSize);
        callback.onResult(movies, params.requestedStartPosition);
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<Movie> callback) {
        List<Movie> movies = mAppRepository.getListMovies(params.startPosition, params.loadSize);
        callback.onResult(movies);
    }
}
