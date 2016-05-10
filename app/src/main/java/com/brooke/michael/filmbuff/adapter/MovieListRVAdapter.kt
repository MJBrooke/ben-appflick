package com.brooke.michael.filmbuff.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.brooke.michael.filmbuff.R
import com.brooke.michael.filmbuff.extensions.inflateLayout
import com.brooke.michael.filmbuff.extensions.loadImage
import com.brooke.michael.filmbuff.extensions.toast
import com.brooke.michael.filmbuff.rest.model.DBWatchListItem
import com.brooke.michael.filmbuff.rest.model.Movie
import com.brooke.michael.filmbuff.rest.model.MovieList
import com.brooke.michael.filmbuff.rest.service.RestClient
import com.orm.query.Select
import kotlinx.android.synthetic.main.card_movie.view.*

class MovieListRVAdapter(val mMovieList: MovieList, val listType: ListType) : RecyclerView.Adapter<MovieListRVAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val view = parent.inflateLayout(R.layout.card_movie)

        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) = holder.bindMovieHolder(mMovieList.results[position])

    override fun getItemCount(): Int = mMovieList.results.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var movieRecord: Movie
        var expanded = false

        fun bindMovieHolder(movie: Movie) {

            movieRecord = movie

            with(itemView){
                with(movie){
                    moviePoster.loadImage(RestClient.POSTER_BASE_URL + poster_path)
                    movieTitle.text = original_title
                    ratingBar.rating = vote_average.toInt()
                    ratingText.text = "$vote_average/10"
                    movieSynopsis.text = getSynopsisText()
                    btnAddToWatchList.text = getButtonText()
                    btnAddToWatchList.setOnClickListener { updateWatchList() }
                }
            }

            itemView.setOnClickListener {

                itemView.movieSynopsis.text = if(!expanded)
                    movieRecord.overview
                else
                    getSynopsisText()

                expanded = !expanded
            }
        }

        private fun requestMovieFromDB() = Select.from(DBWatchListItem::class.java).where("movie_id=${movieRecord.id}").toList()

        private fun getMovie() = requestMovieFromDB()[0] as DBWatchListItem

        private fun getButtonText(): String = if(requestMovieFromDB().isEmpty()) "Add" else "Remove"

        private fun removeMovieFromAdapter(){
            if(listType == ListType.WATCHLIST) {
                mMovieList.results.remove(movieRecord)
                notifyDataSetChanged()
            }
        }

        private fun addMovieToWatchlist(){
            val dbItem = DBWatchListItem(movieRecord)
            dbItem.save()
            toast(itemView, "Movie added!")
        }

        private fun removeMovieFromWatchlist(dbItem: DBWatchListItem){
            dbItem.delete()
            removeMovieFromAdapter()
            toast(itemView, "Movie removed!")
        }

        private fun updateWatchList(){

            val dbRequest = requestMovieFromDB()

            if(dbRequest.isEmpty()){
                addMovieToWatchlist()
            } else {
                removeMovieFromWatchlist(getMovie())
            }

            itemView.btnAddToWatchList.text = getButtonText()
        }

        private fun getSynopsisText(): String {

            val maxCharacters = 190
            val summary = movieRecord.overview

            return if (summary.length > maxCharacters) {

                val indexOfLastSpace = summary.indexOf(" ", maxCharacters)

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
