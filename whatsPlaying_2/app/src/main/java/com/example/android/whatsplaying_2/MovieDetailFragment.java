package com.example.android.whatsplaying_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailFragment extends Fragment {

    public Movie movie;
    public Intent intent;
    Bundle b;
    public MovieDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_movie_detail, container,false);

        TextView title = (TextView) rootView.findViewById(R.id.textTitle);
        TextView releaseDate = (TextView) rootView.findViewById(R.id.textReleaseDate);
        TextView VoteAve = (TextView) rootView.findViewById(R.id.textVoteAve);
        TextView Plot = (TextView) rootView.findViewById(R.id.textPlot);
        ImageView PosterDetail = (ImageView) rootView.findViewById(R.id.moviePosterDetail);



        //grab intent
        Intent i = getActivity().getIntent();
    //get bundle from intent
        b = i.getExtras();

        if (b != null) {
            //get movie object from bundle
            movie = b.getParcelable("selectedMovie");
        }



        title.setText(movie.movieTitle);
        releaseDate.setText("Release Date: "+movie.releaseDate);
        VoteAve.setText("Rating: "+movie.voteAverage);
        String Movieurl = "http://image.tmdb.org/t/p/w185" + movie.moviePoster;
        Picasso.with(getContext()).load(Movieurl).into(PosterDetail);
        Plot.setText(movie.plotSynopsis);

        return rootView;

    }
}
