package com.brooke.michael.filmbuff.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.brooke.michael.filmbuff.R
import com.brooke.michael.filmbuff.extensions.setupDefaultConfig
import com.brooke.michael.filmbuff.extensions.setupSwipeRefresh
import com.brooke.michael.filmbuff.rest.service.MovieListCallback
import com.brooke.michael.filmbuff.rest.service.RestClient

import kotlinx.android.synthetic.main.fragment_this_month.*


class TabFragment(val currentTab: TabType) : Fragment() {

    private val callback by lazy { MovieListCallback(recyclerView, swipeRefresh) }

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
                TabType.HIGHEST_RATED -> getFamilyFriendlyMovies(callback)
                TabType.MOST_POPULAR -> getMostPopularMovies(callback)
            }
        }
    }
}
