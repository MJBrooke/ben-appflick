package com.brooke.michael.filmbuff.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.brooke.michael.filmbuff.R
import com.brooke.michael.filmbuff.rest.model.MovieList
import com.brooke.michael.filmbuff.rest.service.RestClient
import com.squareup.picasso.Picasso

import butterknife.Bind
import butterknife.ButterKnife
import io.techery.properratingbar.ProperRatingBar

/**
 * Created by Michael on 06-Mar-16.
 */
class MovieListRVAdapter : RecyclerView.Adapter<MovieListRVAdapter.MovieViewHolder>() {

    private var mMovieList: MovieList? = null

    fun setMovieList(list: MovieList) {
        mMovieList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_movie, parent, false)

        val holder = MovieViewHolder(view)
        holder.itemView.tag = holder

        return holder
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val movie = mMovieList!!.results[position]

        Picasso.with(holder.imgMovie.context).load(RestClient.POSTER_BASE_URL + movie.posterPath).placeholder(R.drawable.ic_menu_camera).into(holder.imgMovie)

        var movieSummary = movie.overview


        if (movieSummary.length > 150) {
            val indexOfLastSpace = movieSummary.indexOf(" ", 150)
            if (indexOfLastSpace > -1)
                movieSummary = movieSummary.substring(0, indexOfLastSpace) + "... [Click for more]"
        }

        holder.txtTitle.text = movie.originalTitle
        holder.txtSummary.text = movieSummary

        holder.ratingBar.rating = movie.voteAverage.toInt()
        holder.txtRating.text = "${movie.voteAverage}/10"
    }

    override fun getItemCount(): Int {
        return if (mMovieList == null) 0 else mMovieList!!.results.size
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @Bind(R.id.img_movie)
        internal var imgMovie: ImageView

        @Bind(R.id.txt_title)
        internal var txtTitle: TextView

        @Bind(R.id.txt_summary)
        internal var txtSummary: TextView

        @Bind(R.id.txt_rating)
        internal var txtRating: TextView

        @Bind(R.id.rating_bar)
        internal var ratingBar: ProperRatingBar

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}
