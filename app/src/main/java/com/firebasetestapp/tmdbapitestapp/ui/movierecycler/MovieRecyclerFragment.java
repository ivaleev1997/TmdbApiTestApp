package com.firebasetestapp.tmdbapitestapp.ui.movierecycler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebasetestapp.tmdbapitestapp.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class MovieRecyclerFragment extends Fragment {

    private MovieRecyclerViewModel mViewModel;

    public static MovieRecyclerFragment newInstance() {
        return new MovieRecyclerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movie_recycler_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MovieRecyclerViewModel.class);
        // TODO: Use the ViewModel
    }
}
