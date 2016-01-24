package com.example.android.whatsplaying_2;

/**
 * Created by TroysMacBook on 1/18/16.
 */
public class Movie {
    String movieTitle;
    String releaseDate;
    public String moviePoster; //drawable reference id
    String voteAverage;
    String plotSynopsis;

    public Movie (String lmovieTitle, String lreleaseDate, String lmoviePoster, String lvoteAverage, String lplotSynopsis){
        this.movieTitle = lmovieTitle;
        this.releaseDate=lreleaseDate;
        this.moviePoster = lmoviePoster;
        this.voteAverage = lvoteAverage;
        this.plotSynopsis= lplotSynopsis;
    }
}

