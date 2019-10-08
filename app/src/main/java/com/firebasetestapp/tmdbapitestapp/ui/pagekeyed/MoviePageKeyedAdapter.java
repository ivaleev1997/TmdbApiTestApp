package com.firebasetestapp.tmdbapitestapp.ui.pagekeyed;

import android.view.View;
import android.view.ViewGroup;

import com.firebasetestapp.tmdbapitestapp.data.local.Movie;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class MoviePageKeyedAdapter extends PagedListAdapter<Movie, MoviePageKeyedAdapter.MoviePageKeyedViewHolder> {

    public MoviePageKeyedAdapter() {
        this((Movie.DIFF_MOVIE));
    }

    protected MoviePageKeyedAdapter(@NonNull DiffUtil.ItemCallback diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public MoviePageKeyedAdapter.MoviePageKeyedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MoviePageKeyedViewHolder holder, int position) {

    }

    public class MoviePageKeyedViewHolder extends RecyclerView.ViewHolder {

        public MoviePageKeyedViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
