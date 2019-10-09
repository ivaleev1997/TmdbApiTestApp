package com.firebasetestapp.tmdbapitestapp.ui.pagekeyed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.firebasetestapp.tmdbapitestapp.R;
import com.firebasetestapp.tmdbapitestapp.data.local.Movie;
import com.firebasetestapp.tmdbapitestapp.databinding.PageKeyedItemBinding;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
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
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        PageKeyedItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.page_keyed_item, parent, false);
        return new MoviePageKeyedViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviePageKeyedViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public class MoviePageKeyedViewHolder extends RecyclerView.ViewHolder {

        PageKeyedItemBinding binding;

        public MoviePageKeyedViewHolder(PageKeyedItemBinding movieRecyclerItemBinding) {
            super(movieRecyclerItemBinding.getRoot());
            binding = movieRecyclerItemBinding;
        }

        public void bind(Movie movie) {
            binding.setMovie(movie);
        }
    }
}
