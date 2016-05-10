package com.brooke.michael.filmbuff.rest.service

import com.brooke.michael.filmbuff.rest.model.MovieList
import retrofit.Callback
import retrofit.http.GET
import retrofit.http.Query

interface TheMovieDBService {

    @GET("/discover/movie?primary_release_date.gte=2016-03-01&primary_release_date.lte=2016-05-05")
    fun getCurrentMovies(callback: Callback<MovieList>)

    @GET("/discover/movie?sort_by=popularity.desc")
    fun getMostPopularMovies(callback: Callback<MovieList>)

    @GET("/discover/movie?certification_country=US&certification.lte=G&sort_by=popularity.desc")
    fun getFamilyFriendlyMovies(callback: Callback<MovieList>)

    @GET("/search/movie")
    fun getSearchResults(@Query("query") query: String, callback: Callback<MovieList>)
}
