package com.brooke.michael.filmbuff.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.brooke.michael.filmbuff.R
import com.brooke.michael.filmbuff.adapter.MovieListRVAdapter
import com.brooke.michael.filmbuff.rest.model.MovieList
import com.brooke.michael.filmbuff.rest.service.RestClient

import kotlinx.android.synthetic.main.fragment_this_month.*
import retrofit.Callback
import retrofit.RetrofitError
import retrofit.client.Response

/**
 * Created by Michael on 06-Mar-16.
 */
class ThisMonthFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_this_month, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        recycler_view.layoutManager = LinearLayoutManager(activity)
        recycler_view.setHasFixedSize(true)
        val mAdapter = MovieListRVAdapter()

        val service = RestClient.getMovieClient()

        service.getCurrentMovies(object : Callback<MovieList> {
            override fun success(movieList: MovieList, response: Response) {
                mAdapter.setMovieList(movieList)
                recycler_view.adapter = mAdapter
                mAdapter.notifyDataSetChanged()
            }

            override fun failure(error: RetrofitError) {
                Toast.makeText(activity, "API Error.", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
