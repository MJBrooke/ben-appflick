package com.brooke.michael.filmbuff.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.brooke.michael.filmbuff.R
import com.brooke.michael.filmbuff.extensions.inflateLayout
import com.brooke.michael.filmbuff.extensions.loadImage
import com.brooke.michael.filmbuff.rest.model.MovieList
import com.brooke.michael.filmbuff.rest.model.Movie
import com.brooke.michael.filmbuff.rest.service.RestClient
import kotlinx.android.synthetic.main.card_movie.view.*;

class MovieListRVAdapter(val mMovieList: MovieList) : RecyclerView.Adapter<MovieListRVAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val view = parent.inflateLayout(R.layout.card_movie)

        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        holder.bindMovieHolder(mMovieList.results[position])

    }

    override fun getItemCount(): Int {

        return mMovieList.results.size

    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindMovieHolder(movie: Movie) {
            with(itemView){
                with(movie){
                    moviePoster.loadImage(RestClient.POSTER_BASE_URL + poster_path)
                    movieTitle.text = original_title
                    ratingBar.rating = vote_average.toInt()
                    ratingText.text = "$vote_average/10"
                    movieSynopsis.text = getSynopsisText(overview)
                }
            }
        }

        private fun getSynopsisText(summary: String): String {

            return if (summary.length > 150) {

                val indexOfLastSpace = summary.indexOf(" ", 150)

                if (indexOfLastSpace > -1) {
                    "${summary.substring(0, indexOfLastSpace)}... [Click for more]"
                } else {
                    summary
                }

            } else {
                summary
            }
        }
    }
}
