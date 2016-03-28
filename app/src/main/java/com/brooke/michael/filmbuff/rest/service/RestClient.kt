package com.brooke.michael.filmbuff.rest.service

import com.google.gson.GsonBuilder

import retrofit.RestAdapter
import retrofit.converter.GsonConverter

class RestClient {

    companion object {
        const val MOVIE_BASE_URL = "https://api.themoviedb.org/3/"
        const val POSTER_BASE_URL = "http://image.tmdb.org/t/p/w300/"

        val instance = RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(MOVIE_BASE_URL)
                .setConverter(GsonConverter(GsonBuilder().serializeNulls().create()))
                .build()
                .create(TheMovieDBService::class.java)
    }
}
