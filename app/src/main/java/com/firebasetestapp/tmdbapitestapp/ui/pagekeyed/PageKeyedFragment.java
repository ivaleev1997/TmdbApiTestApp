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

public class PageKeyedFragment extends Fragment {

    private PageKeyedViewModel mViewModel;
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this, mAppViewModelFactory).get(PageKeyedViewModel.class);
        // TODO: try DataBinding
        // TODO: finish PageKeyedAdapter
        // TODO: add Recycler, observe livedata from mViewModel..
    }

}
