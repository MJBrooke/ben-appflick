package com.brooke.michael.filmbuff.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.brooke.michael.filmbuff.R
import com.brooke.michael.filmbuff.extensions.inflateLayout
import com.brooke.michael.filmbuff.extensions.loadImage
import com.brooke.michael.filmbuff.extensions.toast
import com.brooke.michael.filmbuff.rest.model.DBWatchListItem
import com.brooke.michael.filmbuff.rest.model.MovieList
import com.brooke.michael.filmbuff.rest.model.Movie
import com.brooke.michael.filmbuff.rest.service.RestClient
import com.orm.query.Select
import kotlinx.android.synthetic.main.card_movie.view.*;

class MovieListRVAdapter(val mMovieList: MovieList, val listType: ListType) : RecyclerView.Adapter<MovieListRVAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val view = parent.inflateLayout(R.layout.card_movie)

        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) = holder.bindMovieHolder(mMovieList.results[position])

    override fun getItemCount(): Int = mMovieList.results.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindMovieHolder(movie: Movie) {

            with(itemView){
                with(movie){
                    moviePoster.loadImage(RestClient.POSTER_BASE_URL + poster_path)
                    movieTitle.text = original_title
                    ratingBar.rating = vote_average.toInt()
                    ratingText.text = "$vote_average/10"
                    movieSynopsis.text = getSynopsisText(overview)
                    btnAddToWatchList.text = getButtonText(movie)
                    btnAddToWatchList.setOnClickListener { updateWatchList(movie) }
                }
            }
        }

        //TODO - Optimise all of this code to call the database only a single time!

        private fun getButtonText(movie: Movie): String {
            val dbRequest = Select.from(DBWatchListItem::class.java).where("movie_id=${movie.id}").toList()

            return if(dbRequest.isEmpty()) "Add" else "Remove"
        }

        private fun updateWatchList(movie: Movie){

            fun addMovieToWatchlist(){
                val dbItem = DBWatchListItem(movie)
                dbItem.save()
                toast(itemView, "Movie successfully added!")
            }

            fun removeMovieFromAdapter(){
                if(listType == ListType.WATCHLIST) {
                    mMovieList.results.remove(movie)
                    this@MovieListRVAdapter.notifyDataSetChanged()
                }
            }

            fun removeMovieFromWatchlist(dbItem: DBWatchListItem){
                dbItem.delete()
                removeMovieFromAdapter()
                toast(itemView, "Movie successfully removed!")
            }

            val dbRequest = Select.from(DBWatchListItem::class.java).where("movie_id=${movie.id}").toList()

            if(dbRequest.isEmpty()){
                addMovieToWatchlist()
            } else {
                removeMovieFromWatchlist(dbRequest[0] as DBWatchListItem)
            }

            itemView.btnAddToWatchList.text = getButtonText(movie)
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
