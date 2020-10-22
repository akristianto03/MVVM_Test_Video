package com.uc.mvvmtestvideo.ui.main.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.uc.mvvmtestvideo.model.Cast;
import com.uc.mvvmtestvideo.model.Genre;
import com.uc.mvvmtestvideo.repository.MovieRepository;

import java.util.List;

public class DetailViewModel extends ViewModel {

    private MovieRepository movieRepository;
    //tvShow

    public DetailViewModel() {
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<List<Genre>> getMovieGenre(int id){
        return movieRepository.getGenreCollection(id);
    }

    public MutableLiveData<List<Cast>> getMovieCast(int id){
        return movieRepository.getCastCollection(id);
    }

}
