package com.brooke.michael.filmbuff.rest.service

import com.google.gson.GsonBuilder

import retrofit.RestAdapter
import retrofit.converter.GsonConverter

object RestClient {

    const val MOVIE_BASE_URL = "https://api.themoviedb.org/3/"
    const val POSTER_BASE_URL = "http://image.tmdb.org/t/p/w300/"

    fun getMovieClient(): TheMovieDBService{
        val gsonConverter = GsonConverter(GsonBuilder().serializeNulls().create())

        val adapter = RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(MOVIE_BASE_URL)
                .setConverter(gsonConverter)
                .build()

        return adapter.create(TheMovieDBService::class.java)
    }
}
