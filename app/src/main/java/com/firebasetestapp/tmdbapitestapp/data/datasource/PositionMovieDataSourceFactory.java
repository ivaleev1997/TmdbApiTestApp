package com.firebasetestapp.tmdbapitestapp.data.datasource;

import com.firebasetestapp.tmdbapitestapp.data.AppRepository;
import com.firebasetestapp.tmdbapitestapp.data.local.Movie;

import androidx.paging.DataSource;

public class PositionMovieDataSourceFactory extends DataSource.Factory<Integer, Movie> {

    private AppRepository mAppRepository;
    private PositionMovieDataSource mCurrentPositionMovieDataSource;

    public PositionMovieDataSourceFactory(AppRepository appRepository) {
        mAppRepository = appRepository;
    }

    @Override
    public DataSource<Integer, Movie> create() {
        return mCurrentPositionMovieDataSource = new PositionMovieDataSource(mAppRepository);
    }
}
