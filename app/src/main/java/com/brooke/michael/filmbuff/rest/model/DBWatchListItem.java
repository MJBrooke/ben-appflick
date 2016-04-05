package com.brooke.michael.filmbuff.rest.model;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

public class DBWatchListItem extends SugarRecord {

    String posterPath;
    String overview;
    String originalTitle;
    int movieId;
    double voteAverage;

    public DBWatchListItem() {
    }

    public int getMovieID(){
        return movieId;
    }

    public DBWatchListItem(Movie movie) {
        posterPath = movie.getPoster_path();
        overview = movie.getOverview();
        originalTitle = movie.getOriginal_title();
        movieId = movie.getId();
        voteAverage = movie.getVote_average();
    }

    public static MovieList getMovieList(List<DBWatchListItem> dbItems){

        ArrayList<Movie> movies = new ArrayList<>();

        for(DBWatchListItem item : dbItems){
            movies.add(new Movie(
                    item.posterPath,
                    item.overview,
                    item.movieId,
                    item.originalTitle,
                    item.voteAverage
            ));
        }

        return new MovieList(0, movies, movies.size(), 0);
    }
}
