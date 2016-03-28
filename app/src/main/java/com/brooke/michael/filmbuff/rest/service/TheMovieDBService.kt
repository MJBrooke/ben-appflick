package com.brooke.michael.filmbuff.rest.service

import com.brooke.michael.filmbuff.rest.model.MovieList

import retrofit.Callback
import retrofit.http.GET

/**
 * Created by Michael on 06-Mar-16.
 */
interface TheMovieDBService {

    @GET("/discover/movie?primary_release_date.gte=2016-02-01&primary_release_date.lte=2016-03-06&api_key=332cc53cb512e33f2caad27caa83d137")
    fun getCurrentMovies(callback: RecyclerViewRefreshCallback)
}
