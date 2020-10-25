package com.uc.mvvmtestvideo.network;

import com.uc.mvvmtestvideo.model.CastResponse;
import com.uc.mvvmtestvideo.model.GenreResponse;
import com.uc.mvvmtestvideo.model.MovieResponse;
import com.uc.mvvmtestvideo.model.TvShow;
import com.uc.mvvmtestvideo.model.TvShowResponse;
import com.uc.mvvmtestvideo.util.Constants;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
//    private static Retrofit retrofit;
//
//    public static <S> S createService(Class<S> serviceClass){
//        if(retrofit == null){
//            retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
//        }
//        return retrofit.create(serviceClass);
//    }

    private ApiEndPoints api;
    private static RetrofitService service;

    private RetrofitService(){
        api = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiEndPoints.class);
    }

    public static RetrofitService getInstance(){
        if(service==null){
            service = new RetrofitService();
        }
        return service;
    }

    public Call<MovieResponse> getMovies(){
        return  api.getMovies(Constants.API_KEY);
    }

    public Call<TvShowResponse> getTvShows() {
        return api.getTvShows(Constants.API_KEY);
    }

    public Call<GenreResponse> getGenre(String type,int id){
        return  api.getGenres(type, id, Constants.API_KEY);
    }

    public Call<CastResponse> getCast(String type, int id) {
        return api.getCasts(type, id, Constants.API_KEY);
    }

}
