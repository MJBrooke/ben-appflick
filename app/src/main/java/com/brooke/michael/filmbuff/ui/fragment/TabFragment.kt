package com.brooke.michael.filmbuff.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.brooke.michael.filmbuff.R
import com.brooke.michael.filmbuff.adapter.ListType
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


class TabFragment(val currentTab: TabType) : Fragment() {

    private val callback by lazy { TabFragmentCallback() }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater!!.inflate(R.layout.fragment_this_month, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        recyclerView.setupDefaultConfig()
        swipeRefresh.setupSwipeRefresh { queryAPI() }
        queryAPI()
    }

    override fun onDestroy() {
        super.onDestroy()
        callback.cancelled = true
    }

    private fun queryAPI() {
        with(RestClient.instance){
            when(currentTab) {
                TabType.THIS_MONTH -> getCurrentMovies(callback)
                TabType.HIGHEST_RATED -> getHighestRatedMovies(callback)
                TabType.MOST_POPULAR -> getMostPopularMovies(callback)
            }
        }
    }

    private inner class TabFragmentCallback() : Callback<MovieList> {

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
            swipeRefresh.isRefreshing = false
        }
    }
}
