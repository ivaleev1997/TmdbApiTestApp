package com.firebasetestapp.tmdbapitestapp.ui.movierecycler;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.firebasetestapp.tmdbapitestapp.R;
import com.firebasetestapp.tmdbapitestapp.data.local.Movie;
import com.firebasetestapp.tmdbapitestapp.databinding.MovieRecyclerItemBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.MovieRecyclerViewHolder> {

    private List<Movie> mMovieList;

    public MovieRecyclerAdapter() {
        mMovieList = new ArrayList<>();
    }

    public List<Movie> getMovieList() {
        return mMovieList;
    }

    @NonNull
    @Override
    public MovieRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MovieRecyclerItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.movie_recycler_item, parent, false);

        return new MovieRecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieRecyclerViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public void setMovieList(List<Movie> movieList) {
        mMovieList = movieList;
    }

    class MovieRecyclerViewHolder extends RecyclerView.ViewHolder {

        MovieRecyclerItemBinding binding;

        public MovieRecyclerViewHolder(@NonNull MovieRecyclerItemBinding movieRecyclerItemBinding) {
            super(movieRecyclerItemBinding.getRoot());
            binding = movieRecyclerItemBinding;
        }

        public void bind(int position) {
            binding.setMovie(mMovieList.get(position));
        }
    }
}
