package com.uc.mvvmtestvideo.util;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

public class Constants {
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String API_KEY = "7f4159addaeabcbd5b0fcdabdd4e4899";
    public static final String IMG_URL = "https://image.tmdb.org/t/p/original";

    @Retention(SOURCE)
    @StringDef
    public @interface Type{
        String MOVIES = "movie";
        String TV_SHOWS = "tv";
    }
}
