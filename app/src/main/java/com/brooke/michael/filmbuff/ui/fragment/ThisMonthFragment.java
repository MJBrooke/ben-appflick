package com.brooke.michael.filmbuff.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.brooke.michael.filmbuff.R;
import com.brooke.michael.filmbuff.adapter.MovieListRVAdapter;
import com.brooke.michael.filmbuff.rest.model.MovieList;
import com.brooke.michael.filmbuff.rest.service.RestClient;
import com.brooke.michael.filmbuff.rest.service.TheMovieDBService;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Michael on 06-Mar-16.
 */
public class ThisMonthFragment extends Fragment {

    @Bind(R.id.recycler_view)
    RecyclerView mRV;

    private MovieListRVAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_this_month, container, false);

        ButterKnife.bind(this, rootView);
        mRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRV.setHasFixedSize(true);
        mAdapter = new MovieListRVAdapter(getActivity());

        TheMovieDBService service = RestClient.getMovieDBClient();
        service.getCurrentMovies(new Callback<MovieList>() {
            @Override
            public void success(MovieList movieList, Response response) {
                mAdapter.setMovieList(movieList);
                mRV.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), "API Error.", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}
