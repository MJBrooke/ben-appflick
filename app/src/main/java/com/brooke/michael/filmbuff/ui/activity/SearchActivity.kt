package com.brooke.michael.filmbuff.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.brooke.michael.filmbuff.R
import com.brooke.michael.filmbuff.extensions.setupDefaultConfig
import com.brooke.michael.filmbuff.extensions.setupSwipeRefresh
import com.brooke.michael.filmbuff.rest.service.MovieListCallback
import com.brooke.michael.filmbuff.rest.service.RestClient
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    private val callback by lazy { MovieListCallback(searchRV, searchSwipeRefresh) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(searchToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        searchRV.setupDefaultConfig()
        searchSwipeRefresh.setupSwipeRefresh { queryAPI() }
        queryAPI()
    }

    override fun onDestroy() {
        super.onDestroy()
        callback.cancelled = true
    }

    private fun queryAPI(){

        val queryString = intent.extras.getString("QUERY_STRING");

        RestClient.instance.getSearchResults(queryString, callback)
    }
}