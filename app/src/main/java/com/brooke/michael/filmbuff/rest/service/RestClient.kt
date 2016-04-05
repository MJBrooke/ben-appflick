package com.brooke.michael.filmbuff.rest.service

import com.google.gson.GsonBuilder

import retrofit.RestAdapter
import retrofit.converter.GsonConverter

class RestClient {

    companion object {
        const val MOVIE_BASE_URL = "https://api.themoviedb.org/3/"
        const val POSTER_BASE_URL = "http://image.tmdb.org/t/p/w300/"
        const val API_KEY = "332cc53cb512e33f2caad27caa83d137"

        val instance = RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(MOVIE_BASE_URL)
                .setConverter(GsonConverter(GsonBuilder().serializeNulls().create()))
                .setRequestInterceptor { it.addQueryParam("api_key", "$API_KEY") }
                .build()
                .create(TheMovieDBService::class.java)
    }
}
