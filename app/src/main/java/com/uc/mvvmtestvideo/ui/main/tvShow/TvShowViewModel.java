package com.uc.mvvmtestvideo.ui.main.tvShow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.uc.mvvmtestvideo.model.TvShow;
import com.uc.mvvmtestvideo.repository.TvShowRepository;

import java.util.List;

public class TvShowViewModel extends ViewModel {

    private final TvShowRepository repository;

    public TvShowViewModel(){
        repository = TvShowRepository.getInstance();
    }

    public LiveData<List<TvShow>> getTvShowCollection(){
        return repository.getTvShowCollection();
    }

}
