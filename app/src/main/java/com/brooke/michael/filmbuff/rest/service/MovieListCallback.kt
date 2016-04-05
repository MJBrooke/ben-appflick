package com.brooke.michael.filmbuff.rest.service

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import com.brooke.michael.filmbuff.R
import com.brooke.michael.filmbuff.adapter.ListType
import com.brooke.michael.filmbuff.adapter.MovieListRVAdapter
import com.brooke.michael.filmbuff.extensions.toast
import com.brooke.michael.filmbuff.rest.model.MovieList
import retrofit.Callback
import retrofit.RetrofitError
import retrofit.client.Response

class MovieListCallback(val recyclerView: RecyclerView, val swipeRefresh: SwipeRefreshLayout? = null)  : Callback<MovieList> {

    var cancelled = false

    override fun success(movieList: MovieList, response: Response) {
        if(!cancelled) {
            onReceive { recyclerView.adapter = MovieListRVAdapter(movieList, ListType.API_LIST) }
        }
    }

    override fun failure(error: RetrofitError) {
        if(!cancelled) {
            onReceive { toast(recyclerView, R.string.api_error) }
        }
    }

    private inline fun onReceive(func: () -> Unit){
        func()
        swipeRefresh?.isRefreshing = false
    }
}