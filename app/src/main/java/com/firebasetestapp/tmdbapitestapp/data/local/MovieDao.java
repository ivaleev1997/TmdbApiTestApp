package com.firebasetestapp.tmdbapitestapp.data.local;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie")
    List<Movie> getMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertMovie(Movie movie);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListMovies(List<Movie> movies);

    @Delete
    void deleteMovie(Movie movie);

    @Query("DELETE FROM movie")
    void deleteAllMovies();

    @Query("SELECT * FROM movie")
    LiveData<List<Movie>> getLiveDataListMovies();
}
