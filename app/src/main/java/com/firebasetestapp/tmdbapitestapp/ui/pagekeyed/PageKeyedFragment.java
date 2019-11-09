package com.firebasetestapp.tmdbapitestapp.ui.pagekeyed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebasetestapp.tmdbapitestapp.R;
import com.firebasetestapp.tmdbapitestapp.ui.AppViewModelFactory;
import com.firebasetestapp.tmdbapitestapp.ui.MainActivity;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.android.support.DaggerFragment;

public class PageKeyedFragment extends DaggerFragment {

    private PageKeyedViewModel mViewModel;
    private MoviePageKeyedAdapter mMoviePageKeyedAdapter;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    @Inject
    AppViewModelFactory mAppViewModelFactory;

    public static PageKeyedFragment newInstance() {
        return new PageKeyedFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.page_keyed_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // TODO: try DataBinding - Done
        // TODO: finish PageKeyedAdapter - Done
        // TODO: add Recycler, observe livedata from mViewModel.. - Done
        // TODO: observe loading status..
        mViewModel = ViewModelProviders.of(this, mAppViewModelFactory).get(PageKeyedViewModel.class);
        mProgressBar = view.findViewById(R.id.page_keyed_recycler_progress_bar);

        mRecyclerView = view.findViewById(R.id.page_keyed_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mMoviePageKeyedAdapter = new MoviePageKeyedAdapter();
        mViewModel.getMovieLiveData().observe(getViewLifecycleOwner(), pagedList -> {
            mProgressBar.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            mMoviePageKeyedAdapter.submitList(pagedList);
        });
        mRecyclerView.setAdapter(mMoviePageKeyedAdapter);

        mViewModel.getStatusLiveData().observe(getViewLifecycleOwner(), resource -> {
            if (resource.isLoading()) Toast.makeText(getContext(), "Loading", Toast.LENGTH_SHORT).show();
            else if (resource.isError()) Toast.makeText(getContext(), resource.message, Toast.LENGTH_LONG).show();
            else if (resource.isSuccess()) Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getContext(), "PageKayed", Toast.LENGTH_SHORT).show();
        if (getActivity() != null) {
            ((MainActivity)getActivity()).fragmentOnResumed(this.getClass().getName());
        }
    }
}
