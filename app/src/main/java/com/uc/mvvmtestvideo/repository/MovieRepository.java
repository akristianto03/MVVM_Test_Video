package com.uc.mvvmtestvideo.repository;

import android.nfc.Tag;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.uc.mvvmtestvideo.model.Cast;
import com.uc.mvvmtestvideo.model.CastResponse;
import com.uc.mvvmtestvideo.model.Genre;
import com.uc.mvvmtestvideo.model.GenreResponse;
import com.uc.mvvmtestvideo.model.Movie;
import com.uc.mvvmtestvideo.model.MovieResponse;
import com.uc.mvvmtestvideo.network.ApiEndPoints;
import com.uc.mvvmtestvideo.network.RetrofitService;
import com.uc.mvvmtestvideo.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private static MovieRepository movieRepository;
//    private ApiEndPoints apiEndPoints;

    private RetrofitService servicel;
    private static final String TAG = MovieRepository.class.getName();

//    public MovieRepository(ApiEndPoints apiEndPoints){
//        this.apiEndPoints = apiEndPoints;
//    }

    private MovieRepository() {
        servicel = RetrofitService.getInstance();
    }

    public static MovieRepository getInstance(){
        if(movieRepository == null){
            movieRepository = new MovieRepository();
        }
        return movieRepository;
    }

    public MutableLiveData<List<Movie>> getMovieCollection(){
        MutableLiveData<List<Movie>> listMovie = new MutableLiveData<>();

        servicel.getMovies().enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        listMovie.postValue(response.body().getResults());
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listMovie;
    }

    public MutableLiveData<List<Genre>> getGenreCollection(int id){
        MutableLiveData<List<Genre>> listGenre = new MutableLiveData<>();

        servicel.getGenre(Constants.Type.MOVIES, id).enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if(response.isSuccessful()){
                    if(response.body() != null){
                        listGenre.postValue(response.body().getGenres());
                    }
                }
            }

            @Override
            public void onFailure(Call<GenreResponse> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());
            }
        });

        return listGenre;
    }

    public MutableLiveData<List<Cast>> getCastCollection(int id){
        MutableLiveData<List<Cast>> listCast = new MutableLiveData<>();

        servicel.getCast(Constants.Type.MOVIES, id).enqueue(new Callback<CastResponse>() {
            @Override
            public void onResponse(Call<CastResponse> call, Response<CastResponse> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if(response.isSuccessful()){
                    if(response.body() != null){
                        Log.d(TAG, "onResponse: "+response.body().getCasts().size());
                        listCast.postValue(response.body().getCasts());
                    }
                }
            }

            @Override
            public void onFailure(Call<CastResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listCast;
    }

}
