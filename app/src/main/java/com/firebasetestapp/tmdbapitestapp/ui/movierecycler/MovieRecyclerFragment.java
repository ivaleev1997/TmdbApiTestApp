package com.firebasetestapp.tmdbapitestapp.ui.movierecycler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebasetestapp.tmdbapitestapp.R;
import com.firebasetestapp.tmdbapitestapp.ui.AppViewModelFactory;
import com.firebasetestapp.tmdbapitestapp.ui.MainActivity;
import com.firebasetestapp.tmdbapitestapp.utils.MovieDiffUtilCallback;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DiffUtil;
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

        return inflater.inflate(R.layout.movie_recycler_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = ViewModelProviders.of(this, mAppViewModelFactory).get(MovieRecyclerViewModel.class);
        mRecyclerView = view.findViewById(R.id.movie_recycler);
        mProgressBar = view.findViewById(R.id.progress_bar);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mMovieRecyclerAdapter = new MovieRecyclerAdapter();
        mRecyclerView.setAdapter(mMovieRecyclerAdapter);
        mViewModel.getListMovieLiveData().observe(getViewLifecycleOwner(), movies -> {
            System.out.println("observer");
            //TODO diffutil - create MovieDiffUtilCallBack extends DiffUtil.CallBack..
            //TODO check work diffUtil ->
            if (mMovieRecyclerAdapter.getMovieList() != null) {
                MovieDiffUtilCallback diffUtilCallback = new MovieDiffUtilCallback(mMovieRecyclerAdapter.getMovieList(), movies);
                DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtilCallback);
                mMovieRecyclerAdapter.setMovieList(movies);
                diffResult.dispatchUpdatesTo(mMovieRecyclerAdapter);
            }

            mProgressBar.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            mMovieRecyclerAdapter.setMovieList(movies);
            mMovieRecyclerAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getContext(), "Simple Recycler", Toast.LENGTH_SHORT).show();
        if (getActivity() != null) {
            ((MainActivity)getActivity()).fragmentOnResumed(this.getClass().getName());
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
