package com.firebasetestapp.tmdbapitestapp.ui.positional;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebasetestapp.tmdbapitestapp.R;
import com.firebasetestapp.tmdbapitestapp.data.local.Movie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import static com.firebasetestapp.tmdbapitestapp.data.local.Movie.DIFF_MOVIE;

public class PositionalPagedFragment extends Fragment {

    private PositionalPagedViewModel mViewModel;

    public static PositionalPagedFragment newInstance() {
        return new PositionalPagedFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.positional_paged_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PositionalPagedViewModel.class);
        // TODO: Use the ViewModel
    }

    public class MoviePositionalAdapter extends PagedListAdapter<Movie, MoviePositionalAdapter.MoviePositionalViewHolder>{

        protected MoviePositionalAdapter() {
            super(DIFF_MOVIE);
        }

        @NonNull
        @Override
        public MoviePositionalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MoviePositionalViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull MoviePositionalViewHolder holder, int position) {

        }

        private class MoviePositionalViewHolder extends RecyclerView.ViewHolder {

            public MoviePositionalViewHolder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }


}
