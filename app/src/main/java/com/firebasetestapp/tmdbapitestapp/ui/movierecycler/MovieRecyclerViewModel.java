package com.firebasetestapp.tmdbapitestapp.ui.movierecycler;

import com.firebasetestapp.tmdbapitestapp.data.AppRepository;
import com.firebasetestapp.tmdbapitestapp.data.local.Movie;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
@Singleton
public class MovieRecyclerViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private AppRepository mAppRepository;
    private LiveData<List<Movie>> mListLiveData;

    @Inject
    public MovieRecyclerViewModel(AppRepository appRepository) {
        mAppRepository = appRepository;
    }

    public LiveData<List<Movie>> getListMovieLiveData() {
        if (mListLiveData == null) mListLiveData = mAppRepository.getMoviesFromDb();

        return mListLiveData;
    }
}
