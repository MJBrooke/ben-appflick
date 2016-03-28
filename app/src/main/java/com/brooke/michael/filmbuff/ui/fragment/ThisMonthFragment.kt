package com.brooke.michael.filmbuff.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.brooke.michael.filmbuff.R
import com.brooke.michael.filmbuff.enum.TAB_TYPE
import com.brooke.michael.filmbuff.extensions.setupDefaultConfig
import com.brooke.michael.filmbuff.extensions.setupSwipeRefresh
import com.brooke.michael.filmbuff.rest.service.RecyclerViewRefreshCallback
import com.brooke.michael.filmbuff.rest.service.RestClient

import kotlinx.android.synthetic.main.fragment_this_month.*


class ThisMonthFragment(val currentTab: TAB_TYPE) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_this_month, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

        recyclerView.setupDefaultConfig(activity)
        swipeRefresh.setupSwipeRefresh { queryAPI() }
        queryAPI()
    }

    private fun queryAPI(){

        val callback = RecyclerViewRefreshCallback(recyclerView)

        when(currentTab){
            TAB_TYPE.THIS_MONTH -> RestClient.instance.getCurrentMovies(callback)
            TAB_TYPE.HIGHEST_RATED -> return
            TAB_TYPE.MOST_POPULAR -> return
        }

        swipeRefresh.isRefreshing = false
    }
}
