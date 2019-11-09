package com.firebasetestapp.tmdbapitestapp.utils;

import com.firebasetestapp.tmdbapitestapp.data.local.Movie;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;

public class MovieDiffUtilCallback extends DiffUtil.Callback {

    private final List<Movie> oldMovieList;
    private final List<Movie> newMovieList;

    public MovieDiffUtilCallback(List<Movie> oldMovieList, List<Movie> newMovieList) {
        this.oldMovieList = oldMovieList;
        this.newMovieList = newMovieList;
    }

    @Override
    public int getOldListSize() {
        return oldMovieList.size();
    }

    @Override
    public int getNewListSize() {
        return newMovieList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Movie oldItem = oldMovieList.get(oldItemPosition);
        Movie newItem = newMovieList.get(newItemPosition);

        return newItem.getId() == oldItem.getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Movie oldItem = oldMovieList.get(oldItemPosition);
        Movie newItem = newMovieList.get(newItemPosition);

        return oldItem.getHeader().equals(newItem.getHeader())
                && oldItem.getReleaseDate().equals(newItem.getReleaseDate())
                && oldItem.getOriginalTitle().equals(newItem.getOriginalTitle());
    }
}
