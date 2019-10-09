package com.firebasetestapp.tmdbapitestapp.ui.pagekeyed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebasetestapp.tmdbapitestapp.R;
import com.firebasetestapp.tmdbapitestapp.ui.AppViewModelFactory;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

public class PageKeyedFragment extends Fragment {

    private PageKeyedViewModel mViewModel;
    private MoviePageKeyedAdapter mMoviePageKeyedAdapter;

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
        mMoviePageKeyedAdapter = new MoviePageKeyedAdapter();
        mViewModel = ViewModelProviders.of(this, mAppViewModelFactory).get(PageKeyedViewModel.class);
        RecyclerView recyclerView = view.findViewById(R.id.page_keyed_recycler);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mMoviePageKeyedAdapter);
        mViewModel.getMovieLiveData().observe(getViewLifecycleOwner(), pagedList -> {
            mMoviePageKeyedAdapter.submitList(pagedList);
        });
        // TODO: try DataBinding - Done
        // TODO: finish PageKeyedAdapter - Done
        // TODO: add Recycler, observe livedata from mViewModel.. - Done
        // TODO: observe loading status..


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}
