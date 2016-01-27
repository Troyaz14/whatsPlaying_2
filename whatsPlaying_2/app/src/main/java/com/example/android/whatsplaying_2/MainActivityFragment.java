package com.example.android.whatsplaying_2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private MovieAdapter movieAdapter;
    public ArrayList<Movie> mList;


    public MainActivityFragment() {
    }
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null || !savedInstanceState.containsKey("movies")) {
            // add movie objects to the array adapter
            mList = new ArrayList<Movie>();
        }
        else {
            mList = savedInstanceState.getParcelableArrayList("movies");
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("movies", mList);
        super.onSaveInstanceState(outState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container,false);

        movieAdapter = new MovieAdapter(getActivity(),mList);

        GridView gridView = (GridView) rootView.findViewById(R.id.gridview);
        gridView.setAdapter(movieAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movie item = movieAdapter.getItem(i);
                //create intent for placeholder fragment
                Intent intent = new Intent();
                
                intent.setClass(getActivity(), MovieDetail.class)
                        .putExtra("selectedMovie",item);
                startActivity(intent);

            }
        });

        return rootView;
    }
    
    public void onStart(){
        //pull Json information
        loadMovies();
        super.onStart();

    }

    public void loadMovies(){
        new loadMovieInfo().execute();
    }

    public class loadMovieInfo extends AsyncTask<Void, Void, Movie[]> {

        private final String LOG_TAG = MainActivityFragment.class.getSimpleName();

        protected Movie[] doInBackground(Void... params) {


            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String movieString = null;
            String sortOrder;
            Uri.Builder builder;

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            sortOrder = prefs.getString(getString(R.string.sort_key), getString(R.string.default_sort_value));

            try {
                //building path to website to pull data
                if(sortOrder.equalsIgnoreCase("Most Popular")) {
                    builder = new Uri.Builder();
                    builder.scheme("https")
                            .authority("api.themoviedb.org")
                            .appendPath("3")
                            .appendPath("discover")
                            .appendPath("movie")
                            .appendQueryParameter("sort_by", "popularity.desc")
                            .appendQueryParameter("api_key", BuildConfig.Movie_db_api_key);
                }else{
                    builder = new Uri.Builder();
                    builder.scheme("https")
                            .authority("api.themoviedb.org")
                            .appendPath("3")
                            .appendPath("discover")
                            .appendPath("movie")
                            .appendQueryParameter("sort_by", "vote_average.desc")
                            .appendQueryParameter("api_key", BuildConfig.Movie_db_api_key);
                }
                String myUrl = builder.build().toString();
                System.out.println("URL: "+myUrl);
                //constructing URL for MovieDB
                URL url = new URL(myUrl);

                // Create the request to MovieDB, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                //buffer reader to improve performance
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {

                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream is empty.
                    return null;
                }
                movieString = buffer.toString();
                System.out.println("Movies" + movieString);


            } catch (
                    IOException e


                    )

            {
                Log.e("loadMovieInfo", "Error ", e);
                return null;
            } finally

            {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("loadMovieInfo", "Error closing stream", e);

                    }
                }
            }

                     try {
                         return getMovieData(movieString);
                     } catch (JSONException e) {
                          Log.e(LOG_TAG, e.getMessage(), e);
                         e.printStackTrace();

                     }
            //if there is an error parsing the forecast
            return null;

        }

        private Movie[] getMovieData(String movieJsonStr) throws JSONException{
            final String getResults = "results";
            final String lmovieTitle = "title";
            final String lreleaseDate = "release_date";
            final String lmoviePoster = "poster_path";
            final String lvoteAverage = "vote_average";
            final String lplotSynopsis = "overview";
            final String id = "id";

            //get movie info and put it into a JSONArray
            JSONObject movieJSON = new JSONObject(movieJsonStr);
            JSONArray movieArray = movieJSON.getJSONArray(getResults);

            //this will hold movie objects
            Movie[] moviesPlaying = new Movie[movieArray.length()];


            for(int i=0; i<movieArray.length(); i++){


                //get individual movie info
                JSONObject movie = movieArray.getJSONObject(i);

                //create strings to pass to object
                String title = movie.getString(lmovieTitle);
                String releaseDate = movie.getString(lreleaseDate);
                String moviePoster = movie.getString(lmoviePoster);
                String voteAverage = movie.getString(lvoteAverage);
                String plot = movie.getString(lplotSynopsis);
                String movieid = movie.getString(id);

                //create object
                moviesPlaying[i] = new Movie(title, releaseDate, moviePoster, voteAverage, plot,movieid);
            }

            return moviesPlaying;
        }

        protected void onPostExecute(Movie[] result) {

            if (result !=null){
                movieAdapter.clear();
                for (Movie dayForecastStr : result){
                    movieAdapter.add(dayForecastStr);
                }
            }

        }

    }


}
