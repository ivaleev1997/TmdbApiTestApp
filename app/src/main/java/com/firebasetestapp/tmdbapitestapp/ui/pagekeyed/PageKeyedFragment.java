package com.firebasetestapp.tmdbapitestapp.ui.pagekeyed;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebasetestapp.tmdbapitestapp.R;

public class PageKeyedFragment extends Fragment {

    private PageKeyedViewModel mViewModel;

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
        mViewModel = ViewModelProviders.of(this).get(PageKeyedViewModel.class);
        // TODO: Use the ViewModel
    }

}
