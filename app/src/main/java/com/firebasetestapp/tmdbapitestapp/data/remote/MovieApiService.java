package com.firebasetestapp.tmdbapitestapp.data.remote;


import com.firebasetestapp.tmdbapitestapp.data.local.Movie;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {

    @GET("movie/popular?api_key=aa7b2f0df06cb6ccf1cbcf705bcf9892&language=en-US&page=1")
    Observable<MovieApiResponse<Movie>> fetchPopularMovies();

    @GET("movie/popular?api_key=aa7b2f0df06cb6ccf1cbcf705bcf9892&language=en-US")
    Observable<MovieApiResponse<Movie>> fetchPopularMoviesByPage(@Query("page") long page);
}
