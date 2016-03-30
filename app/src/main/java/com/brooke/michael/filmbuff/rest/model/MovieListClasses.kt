package com.brooke.michael.filmbuff.rest.model

import java.util.ArrayList

data class MovieList(val page: Int, val results: ArrayList<Movie>, val total_results: Int, val total_pages: Int)

data class Movie(val poster_path: String, val overview: String,
                 val id: Int, val original_title: String, val vote_average: Double)
