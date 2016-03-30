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
import com.brooke.michael.filmbuff.rest.model.DBWatchListItem
import kotlinx.android.synthetic.main.fragment_this_month.*
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

class WatchlistFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater!!.inflate(R.layout.fragment_watch_list, container, false)


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        recyclerView.setupDefaultConfig()
        setupAdapterFromDB()
    }

    private fun setupAdapterFromDB(){
        async() {

            val dbEntries = DBWatchListItem.listAll(DBWatchListItem::class.java)

            val movieList = DBWatchListItem.getMovieList(dbEntries)

            uiThread {
                swipeRefresh.isRefreshing = false
                recyclerView.adapter = MovieListRVAdapter(movieList, ListType.WATCHLIST)
            }
        }
    }


}