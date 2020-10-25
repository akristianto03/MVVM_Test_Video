package com.uc.mvvmtestvideo.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.uc.mvvmtestvideo.model.Cast;
import com.uc.mvvmtestvideo.model.CastResponse;
import com.uc.mvvmtestvideo.model.Genre;
import com.uc.mvvmtestvideo.model.GenreResponse;
import com.uc.mvvmtestvideo.model.TvShow;
import com.uc.mvvmtestvideo.model.TvShowResponse;
import com.uc.mvvmtestvideo.network.RetrofitService;
import com.uc.mvvmtestvideo.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowRepository {

    private static TvShowRepository tvShowRepository;
    private RetrofitService service;
    private static final String TAG = TvShowRepository.class.getName();

    private TvShowRepository() {
        service = RetrofitService.getInstance();
    }

    public static TvShowRepository getInstance() {
        if(tvShowRepository == null){
            tvShowRepository = new TvShowRepository();
        }
        return tvShowRepository;
    }

    public MutableLiveData<List<TvShow>> getTvShowCollection(){
        MutableLiveData<List<TvShow>> listTvShow = new MutableLiveData<>();

        service.getTvShows().enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        listTvShow.postValue(response.body().getResults());
                    }
                }
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listTvShow;
    }

    public LiveData<List<Genre>> getGenreCollection(int id){
        MutableLiveData<List<Genre>> listGenre = new MutableLiveData<>();

        service.getGenre(Constants.Type.TV_SHOWS, id).enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if(response.isSuccessful()){
                    if(response.body() != null){
                        Log.d(TAG, "onResponse: " + response.body().getGenres().size());
                        listGenre.postValue(response.body().getGenres());
                    }
                }
            }

            @Override
            public void onFailure(Call<GenreResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listGenre;
    }

    public MutableLiveData<List<Cast>> getCastCollection(int id){
        MutableLiveData<List<Cast>> listCast = new MutableLiveData<>();

        service.getCast(Constants.Type.TV_SHOWS, id).enqueue(new Callback<CastResponse>() {
            @Override
            public void onResponse(Call<CastResponse> call, Response<CastResponse> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if(response.body() != null){
                    Log.d(TAG, "onResponse: " + response.body().getCasts().size());
                    listCast.postValue(response.body().getCasts());
                }
            }

            @Override
            public void onFailure(Call<CastResponse> call, Throwable t) {

            }
        });

        return listCast;
    }

}
