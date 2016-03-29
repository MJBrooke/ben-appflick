package com.brooke.michael.filmbuff.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.brooke.michael.filmbuff.R
import com.brooke.michael.filmbuff.adapter.MovieListRVAdapter
import com.brooke.michael.filmbuff.enum.TAB_TYPE
import com.brooke.michael.filmbuff.extensions.setupDefaultConfig
import com.brooke.michael.filmbuff.extensions.setupSwipeRefresh
import com.brooke.michael.filmbuff.extensions.toast
import com.brooke.michael.filmbuff.rest.model.MovieList
import com.brooke.michael.filmbuff.rest.service.RestClient

import kotlinx.android.synthetic.main.fragment_this_month.*
import retrofit.Callback
import retrofit.RetrofitError
import retrofit.client.Response


class TabFragment(val currentTab: TAB_TYPE) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater!!.inflate(R.layout.fragment_this_month, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        recyclerView.setupDefaultConfig(activity)
        swipeRefresh.setupSwipeRefresh { queryAPI() }
        queryAPI()
    }

    private fun queryAPI() = with(RestClient.instance){
        val callback = TabFragmentCallback()

        when(currentTab){
            TAB_TYPE.THIS_MONTH -> getCurrentMovies(callback)
            TAB_TYPE.HIGHEST_RATED -> getHighestRatedMovies(callback)
            TAB_TYPE.MOST_POPULAR -> getMostPopularMovies(callback)
        }
    }

    private inner class TabFragmentCallback() : Callback<MovieList> {

        override fun success(movieList: MovieList, response: Response) {
            onReceive { recyclerView.adapter = MovieListRVAdapter(movieList) }
        }

        override fun failure(error: RetrofitError) {
            onReceive { toast(recyclerView, R.string.api_error) }
        }

        private inline fun onReceive(func: () -> Unit){
            func()
            swipeRefresh.isRefreshing = false
        }
    }
}
