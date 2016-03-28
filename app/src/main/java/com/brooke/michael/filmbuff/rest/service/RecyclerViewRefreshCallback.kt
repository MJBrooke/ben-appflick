package com.brooke.michael.filmbuff.rest.service

import android.support.v7.widget.RecyclerView
import com.brooke.michael.filmbuff.adapter.MovieListRVAdapter
import com.brooke.michael.filmbuff.extensions.toast
import com.brooke.michael.filmbuff.rest.model.MovieList
import retrofit.Callback
import retrofit.RetrofitError
import retrofit.client.Response

/**
 * Created by michaelbrooke on 2016/03/28.
 */
open class RecyclerViewRefreshCallback(val rv: RecyclerView) : Callback<MovieList> {

    override fun success(movieList: MovieList, response: Response) {
        rv.adapter = MovieListRVAdapter(movieList)
    }

    override fun failure(error: RetrofitError) {
        toast(rv, "Success")
    }
}