package com.firebasetestapp.tmdbapitestapp.ui.positional;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebasetestapp.tmdbapitestapp.R;
import com.firebasetestapp.tmdbapitestapp.data.local.Movie;
import com.firebasetestapp.tmdbapitestapp.databinding.PositionalPagedItemBinding;
import com.firebasetestapp.tmdbapitestapp.ui.AppViewModelFactory;
import com.firebasetestapp.tmdbapitestapp.ui.MainActivity;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.android.support.DaggerFragment;

import static com.firebasetestapp.tmdbapitestapp.data.local.Movie.DIFF_MOVIE;

public class PositionalPagedFragment extends DaggerFragment {

    @Inject
    AppViewModelFactory mAppViewModelFactory;

    private PositionalPagedViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private MoviePositionalAdapter mMoviePositionalAdapter;

    public static PositionalPagedFragment newInstance() {
        return new PositionalPagedFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.positional_paged_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = ViewModelProviders.of(this, mAppViewModelFactory).get(PositionalPagedViewModel.class);

        mRecyclerView = view.findViewById(R.id.positional_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mMoviePositionalAdapter = new MoviePositionalAdapter();
        mViewModel.getPagedListLiveData().observe(getViewLifecycleOwner(), pagedList -> {
             mMoviePositionalAdapter.submitList(pagedList);
        });
        mRecyclerView.setAdapter(mMoviePositionalAdapter);
    }


    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getContext(), "Positional", Toast.LENGTH_SHORT).show();
        if (getActivity() != null) {
            ((MainActivity)getActivity()).fragmentOnResumed(this.getClass().getName());
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public class MoviePositionalAdapter extends PagedListAdapter<Movie, MoviePositionalAdapter.MoviePositionalViewHolder>{

        public MoviePositionalAdapter() {
            this(DIFF_MOVIE);
        }

        public MoviePositionalAdapter(@NonNull DiffUtil.ItemCallback<Movie> diffCallback) {
            super(diffCallback);
        }

        @NonNull
        @Override
        public MoviePositionalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            PositionalPagedItemBinding positioanlPagedItemBinding = DataBindingUtil.inflate(inflater, R.layout.positional_paged_item, parent, false);

            return new MoviePositionalViewHolder(positioanlPagedItemBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull MoviePositionalViewHolder holder, int position) {
            if (getItem(position) != null)
                holder.bind(getItem(position));
        }

        private class MoviePositionalViewHolder extends RecyclerView.ViewHolder {
            private PositionalPagedItemBinding binding;

            public MoviePositionalViewHolder(@NonNull PositionalPagedItemBinding positioanlPagedItemBinding) {
                super(positioanlPagedItemBinding.getRoot());
                binding = positioanlPagedItemBinding;
            }

            public void bind(Movie movie) {
                binding.setMovie(movie);
            }
        }
    }
}
