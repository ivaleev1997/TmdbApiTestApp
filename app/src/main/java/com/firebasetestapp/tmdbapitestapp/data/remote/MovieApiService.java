package com.firebasetestapp.tmdbapitestapp.data.remote;


import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MovieApiService {

    @GET("movie/popular?api_key=aa7b2f0df06cb6ccf1cbcf705bcf9892&language=en-US&page=1")
    Observable<MovieApiResponse> fetchPopularMovies();
}
