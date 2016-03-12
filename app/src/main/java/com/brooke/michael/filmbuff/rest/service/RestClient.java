package com.brooke.michael.filmbuff.rest.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by Michael on 06-Mar-16.
 */
public class RestClient {

    private static final String MOVIE_BASE_URL = "https://api.themoviedb.org/3/";
    public static final String POSTER_BASE_URL = "http://image.tmdb.org/t/p/w300/";

    public static TheMovieDBService getMovieDBClient(){

        Gson gsonConverter = new GsonBuilder()
                .serializeNulls()
                .create();

        RestAdapter adapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(MOVIE_BASE_URL)
                .setConverter(new GsonConverter(gsonConverter))
                .build();

        return adapter.create(TheMovieDBService.class);
    }

}
