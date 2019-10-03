package com.firebasetestapp.tmdbapitestapp.ui.movierecycler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.firebasetestapp.tmdbapitestapp.R;
import com.firebasetestapp.tmdbapitestapp.ui.AppViewModelFactory;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.android.support.DaggerFragment;

public class MovieRecyclerFragment extends DaggerFragment {

    @Inject
    AppViewModelFactory mAppViewModelFactory;

    private MovieRecyclerViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private MovieRecyclerAdapter mMovieRecyclerAdapter;
    private ProgressBar mProgressBar;

    public static MovieRecyclerFragment newInstance() {
        return new MovieRecyclerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_recycler_fragment, container, false);
        mRecyclerView = view.findViewById(R.id.movie_recycler);
        mProgressBar = view.findViewById(R.id.progress_bar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this, mAppViewModelFactory).get(MovieRecyclerViewModel.class);
        mMovieRecyclerAdapter = new MovieRecyclerAdapter();
        mRecyclerView.setAdapter(mMovieRecyclerAdapter);
        mViewModel.getListMovieLiveData().observe(getViewLifecycleOwner(), movies -> {
            System.out.println("observer");
            //TODO diffutil
            mProgressBar.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            mMovieRecyclerAdapter.setMovieList(movies);
            mMovieRecyclerAdapter.notifyDataSetChanged();
        });
    }
}
