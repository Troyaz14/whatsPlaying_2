package com.example.android.whatsplaying;

/**
 * Created by TroysMacBook on 1/17/16.
 */
public class movie {
    String movieTitle;
    String releaseDate;
    int moviePoster; //drawable reference id
    double voteAverage;
    String plotSynopsis;

    public movie (String lmovieTitle, String lreleaseDate, int lmoviePoster, double lvoteAverage, String lplotSynopsis){
        this.movieTitle = lmovieTitle;
        this.releaseDate=lreleaseDate;
        this.moviePoster = lmoviePoster;
        this.voteAverage = lvoteAverage;
        this.plotSynopsis= lplotSynopsis;
    }
}
