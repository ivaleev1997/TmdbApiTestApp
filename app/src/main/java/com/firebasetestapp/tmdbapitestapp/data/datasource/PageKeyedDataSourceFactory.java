package com.firebasetestapp.tmdbapitestapp.data.datasource;

import com.firebasetestapp.tmdbapitestapp.data.local.Movie;

import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;


public class PageKeyedDataSourceFactory extends PageKeyedDataSource.Factory<Long, Movie> {



    @Override
    public DataSource<Long, Movie> create() {
        return null;
    }
}
