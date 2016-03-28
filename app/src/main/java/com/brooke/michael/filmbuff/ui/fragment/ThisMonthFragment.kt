package com.brooke.michael.filmbuff.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.brooke.michael.filmbuff.R
import com.brooke.michael.filmbuff.adapter.MovieListRVAdapter
import com.brooke.michael.filmbuff.extensions.setupDefaultConfig
import com.brooke.michael.filmbuff.extensions.setupSwipeRefresh
import com.brooke.michael.filmbuff.extensions.toast
import com.brooke.michael.filmbuff.rest.model.MovieList
import com.brooke.michael.filmbuff.rest.service.RestClient

import kotlinx.android.synthetic.main.fragment_this_month.*

import retrofit.Callback
import retrofit.RetrofitError
import retrofit.client.Response

class ThisMonthFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_this_month, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

        recyclerView.setupDefaultConfig(activity)
        swipeRefresh.setupSwipeRefresh { queryAPI() }
        queryAPI()
    }

    private fun queryAPI(){
        RestClient.instance.getCurrentMovies(object : Callback<MovieList> {
            override fun success(movieList: MovieList, response: Response) {
                recyclerView.adapter = MovieListRVAdapter(movieList)
                swipeRefresh.isRefreshing = false
            }

            override fun failure(error: RetrofitError) {
                swipeRefresh.isRefreshing = false
                toast("API Error")
            }
        })
    }
}
