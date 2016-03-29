package com.brooke.michael.filmbuff.rest.service

import com.brooke.michael.filmbuff.rest.model.MovieList
import retrofit.Callback
import retrofit.http.GET

interface TheMovieDBService {

    @GET("/discover/movie?primary_release_date.gte=2016-02-01&primary_release_date.lte=2016-03-06&api_key=332cc53cb512e33f2caad27caa83d137")
    fun getCurrentMovies(callback: Callback<MovieList>)

    @GET("/discover/movie?sort_by=popularity.desc&api_key=332cc53cb512e33f2caad27caa83d137")
    fun getMostPopularMovies(callback: Callback<MovieList>)

    @GET("/discover/movie?certification_country=US&sort_by=vote_average.desc&api_key=332cc53cb512e33f2caad27caa83d137")
    fun getHighestRatedMovies(callback: Callback<MovieList>)
}
