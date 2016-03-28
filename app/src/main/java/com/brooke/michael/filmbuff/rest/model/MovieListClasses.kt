package com.brooke.michael.filmbuff.rest.model

import java.util.*

data class MovieList(val page: Int, val results: ArrayList<Movie>, val total_results: Int, val total_pages: Int)

//Representation of just the JSON fields we need for the app
data class Movie(val poster_path: String, val overview: String, val genre_ids: List<Int>,
                 val id: Int, val original_title: String, val vote_average: Double)

//Complete representation of the json
/*data class Movie(val poster_path: String, val adult: String, val overview: String, val release_date: String,
                 val genre_ids: List<Int>, val id: Int, val original_title: String, val original_language: String,
                 val title: String, val backdrop_path: String, val popularity: Double, val vote_count: Int,
                 val video: Boolean, val vote_average: Double)*/
