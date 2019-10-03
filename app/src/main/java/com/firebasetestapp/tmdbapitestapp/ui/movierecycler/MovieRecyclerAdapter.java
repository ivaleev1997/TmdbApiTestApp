package com.firebasetestapp.tmdbapitestapp.ui.movierecycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebasetestapp.tmdbapitestapp.R;
import com.firebasetestapp.tmdbapitestapp.data.local.Movie;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.MovieRecyclerViewHolder> {

    private List<Movie> mMovieList;

    public MovieRecyclerAdapter() {
        mMovieList = new ArrayList<>();
    }

    @NonNull
    @Override
    public MovieRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_recycler_item, parent, false);

        return new MovieRecyclerViewHolder(view);
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
        ImageView imageView;
        TextView nameMovieTextView;
        TextView releaseTextView;

        public MovieRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.movie_icon);
            nameMovieTextView = itemView.findViewById(R.id.movie_name);
            releaseTextView = itemView.findViewById(R.id.movie_release);
        }

        public void bind(int position) {
            nameMovieTextView.setText(mMovieList.get(position).getOriginalTitle());
            releaseTextView.setText(mMovieList.get(position).getReleaseDate());
        }
    }
}
