package com.example.android.whatsplaying_2;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TroysMacBook on 1/18/16.
 */
public class Movie implements Parcelable{
    String movieTitle;
    String releaseDate;
    String moviePoster; //drawable reference id
    String voteAverage;
    String plotSynopsis;
    String id;

    public Movie (String lmovieTitle, String lreleaseDate, String lmoviePoster, String lvoteAverage, String lplotSynopsis, String movieId){
        this.movieTitle = lmovieTitle;
        this.releaseDate=lreleaseDate;
        this.moviePoster = lmoviePoster;
        this.voteAverage = lvoteAverage;
        this.plotSynopsis= lplotSynopsis;
        this.id = movieId;
    }
    private Movie(Parcel in){
        movieTitle = in.readString();
        releaseDate = in.readString();
        moviePoster=in.readString();
        voteAverage=in.readString();
        plotSynopsis=in.readString();
        id = in.readString();
    }
    @Override
    public int describeContents(){
        return 0;
    }
    public String toString() { return movieTitle + "--" + releaseDate + "--" + moviePoster + "--"+voteAverage+"--"+plotSynopsis +"--"+id; }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(movieTitle);
        parcel.writeString(releaseDate);
        parcel.writeString(moviePoster);
        parcel.writeString(voteAverage);
        parcel.writeString(plotSynopsis);
        parcel.writeString(id);
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }
        @Override
        public Movie[] newArray(int i) {
            return new Movie[i];
        }
    };
}

