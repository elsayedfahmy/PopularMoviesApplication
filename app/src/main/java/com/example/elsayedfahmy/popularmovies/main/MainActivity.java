package com.example.elsayedfahmy.popularmovies.main;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.elsayedfahmy.popularmovies.*;
import com.example.elsayedfahmy.popularmovies.Details.movie_detail;
import com.example.elsayedfahmy.popularmovies.R;
import com.example.elsayedfahmy.popularmovies.SqlData.SqliteUtil;
import com.example.elsayedfahmy.popularmovies.SqlData.movie_data_favorite;
import com.example.elsayedfahmy.popularmovies.adapter.ImageAdapter;
import com.example.elsayedfahmy.popularmovies.model.MovieResponse;
import com.example.elsayedfahmy.popularmovies.model.movie;
import com.example.elsayedfahmy.popularmovies.rest.APIClient;
import  com.example.elsayedfahmy.popularmovies.rest.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       getMenuInflater().inflate(R.menu.menu_main, menu);




        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //----------------------------------------
        // TODO - insert your themoviedb.org API KEY here
          final String TAG = MainActivity.class.getSimpleName();

  final GridView gridView=MainActivityFragment.gridView;
        final Boolean wifi= MainActivityFragment.wifi;
        final RelativeLayout relativeLayout=MainActivityFragment.relativeLayout;
         String API_KEY = "83900e399daa5d56e8aaefb7871cf094";



        if (id==R.id.top_Rated) {
            if (wifi)
            {
                APIInterface apiService = APIClient.getClient().create(APIInterface.class);
            Call<MovieResponse> call = apiService.getTopRatedMovies(API_KEY);
            call.enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    List<movie> movies = response.body().getResults();

                    gridView.setAdapter(new ImageAdapter(getApplicationContext(), movies));

                    Log.d(TAG, "Number of movies received: " + movies.size());
                }

                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_LONG).show();
                    // Log error here since request failed
                    Log.e(TAG, t.toString());
                }
            });

            Toast.makeText(getApplicationContext(), "Top Rated Mpvies", Toast.LENGTH_LONG).show();

        }
            else {
                Toast.makeText(getApplicationContext(), "No Internet... Please obtain wifi or mobileData", Toast.LENGTH_LONG).show();
            }
            return true;

        }
        ///---------------------------------------------
        if (id==R.id.pupolar) {

            if (wifi) {
                APIInterface apiService = APIClient.getClient().create(APIInterface.class);
            Call<MovieResponse> call = apiService.getPopularMovies(API_KEY);
            call.enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    List<movie> movies = response.body().getResults();

                    gridView.setAdapter(new ImageAdapter(getApplicationContext(), movies));

                    Log.d(TAG, "Number of movies received: " + movies.size());
                }

                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_LONG).show();
                    // Log error here since request failed
                    Log.e(TAG, t.toString());
                }
            });
            Toast.makeText(getApplicationContext(), "Poular Movies", Toast.LENGTH_LONG).show();
        }
            else {
            Toast.makeText(getApplicationContext(), "No Internet... Please obtain wifi or mobileData", Toast.LENGTH_LONG).show();
        }

            return true;
        }
        if (id == R.id.favoritemovies)
        {

        relativeLayout.setBackgroundColor(Color.BLUE);
            SqliteUtil util = new SqliteUtil(getApplicationContext());
            SQLiteDatabase sqLiteDatabase = util.getWritableDatabase();
            List<movie> movies=new ArrayList<>();
            Cursor c = sqLiteDatabase.query(movie_data_favorite.TABLE_NAME, null, null, null, null, null,movie_data_favorite.KEY_ID);
            if (c.moveToFirst()) {
                do {

                    int m = c.getInt(c.getColumnIndex(movie_data_favorite.KEY_ID));
                    String path = c.getString(c.getColumnIndex(movie_data_favorite.path));
                    String title = c.getString(c.getColumnIndex(movie_data_favorite.title));
                    String releaseDate = c.getString(c.getColumnIndex(movie_data_favorite.releaseDate));
                    String description = c.getString(c.getColumnIndex(movie_data_favorite.description));
                    int adult  = c.getInt(c.getColumnIndex(movie_data_favorite.adult));
                    String original_title = c.getString(c.getColumnIndex(movie_data_favorite.original_title));
                    String original_language = c.getString(c.getColumnIndex(movie_data_favorite.original_language));
                    String backdrop_path = c.getString(c.getColumnIndex(movie_data_favorite.backdrop_path));
                    double popularity = c.getDouble(c.getColumnIndex(movie_data_favorite.popularity));
                    int vote_count = c.getInt(c.getColumnIndex(movie_data_favorite.vote_count));
                    int video = c.getInt(c.getColumnIndex(movie_data_favorite.video));
                    String vote_average = c.getString(c.getColumnIndex(movie_data_favorite.vote_average));
                    int movie_id=c.getInt(c.getColumnIndex(movie_data_favorite.movie_id));
                    Boolean video1,adult1;
                    if (adult==1)
                    {adult1=true;

                    }else
                        adult1=false;
                    if (video==1)
                    {video1=true;

                    }else
                        video1=false;
                    movies.add(new movie(path,adult1,description,releaseDate,null,movie_id,original_title,original_language,
                            title,backdrop_path,popularity,vote_count,video1,vote_average));

                } while (c.moveToNext());
            }

            gridView.setAdapter(new ImageAdapter(getApplicationContext(),movies));

            Toast.makeText(getApplicationContext(), "Your Favorite Movies", Toast.LENGTH_LONG).show();

            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
