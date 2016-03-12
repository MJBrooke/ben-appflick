package com.brooke.michael.filmbuff.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brooke.michael.filmbuff.R;
import com.brooke.michael.filmbuff.rest.model.MovieList;
import com.brooke.michael.filmbuff.rest.service.RestClient;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.techery.properratingbar.ProperRatingBar;

/**
 * Created by Michael on 06-Mar-16.
 */
public class MovieListRVAdapter extends RecyclerView.Adapter<MovieListRVAdapter.MovieViewHolder>{

    private MovieList mMovieList;
    private Context mContext;

    public void setMovieList(MovieList list){
        mMovieList = list;
    }

    public MovieListRVAdapter(Context c){
        mContext = c;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie, parent, false);

        MovieViewHolder holder = new MovieViewHolder(view);
        holder.itemView.setTag(holder);

        return holder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        MovieList.Result movie = mMovieList.getResults().get(position);

        Picasso.with(holder.imgMovie.getContext())
                .load(RestClient.POSTER_BASE_URL + movie.getPosterPath())
                .placeholder(R.drawable.ic_menu_camera)
                .into(holder.imgMovie);

        String movieSummary = movie.getOverview();

        if(movieSummary.length() > 150) {
            int indexOfLastSpace = movieSummary.indexOf(" ", 150);
            if(indexOfLastSpace > -1)
                movieSummary = movieSummary.substring(0, indexOfLastSpace) + "... [Click for more]";
        }

        holder.txtTitle.setText(movie.getOriginalTitle());
        holder.txtSummary.setText(movieSummary);

        holder.ratingBar.setRating((int)movie.getVoteAverage());
        holder.txtRating.setText(movie.getVoteAverage() + "/10");

    }

    @Override
    public int getItemCount() {
        return mMovieList == null ? 0 : mMovieList.getResults().size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{

        @Bind (R.id.img_movie)
        ImageView imgMovie;

        @Bind(R.id.txt_title)
        TextView txtTitle;

        @Bind(R.id.txt_summary)
        TextView txtSummary;

        @Bind(R.id.txt_rating)
        TextView txtRating;

        @Bind(R.id.rating_bar)
        ProperRatingBar ratingBar;

        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
