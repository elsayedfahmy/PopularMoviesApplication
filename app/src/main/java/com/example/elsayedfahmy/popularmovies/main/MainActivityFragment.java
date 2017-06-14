package com.example.elsayedfahmy.popularmovies.main;
import com.example.elsayedfahmy.popularmovies.Details.movie_detail;
import com.example.elsayedfahmy.popularmovies.SqlData.SqliteUtil;
import com.example.elsayedfahmy.popularmovies.SqlData.movie_data_favorite;
import com.example.elsayedfahmy.popularmovies.model.movie;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.example.elsayedfahmy.popularmovies.Details.*;
import com.example.elsayedfahmy.popularmovies.R;
import com.example.elsayedfahmy.popularmovies.adapter.ImageAdapter;
import com.example.elsayedfahmy.popularmovies.model.MovieResponse;
import com.example.elsayedfahmy.popularmovies.rest.APIClient;
import com.example.elsayedfahmy.popularmovies.rest.APIInterface;
import com.example.elsayedfahmy.popularmovies.trailermodel.*;
import retrofit2.Call;
import retrofit2.Callback;
import  retrofit2.Response;

import java.util.ArrayList;
import java.util.List;



/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    // TODO - insert your themoviedb.org API KEY here
    private static final String TAG = MainActivity.class.getSimpleName();
    // TODO - insert your themoviedb.org API KEY here
    public final static String API_KEY = "83900e399daa5d56e8aaefb7871cf094";
    List<movie> movies;
    List<trailer> trailermovies;
    public static GridView gridView;
    public static Boolean wifi;
    public static RelativeLayout relativeLayout;
    View root;
    public MainActivityFragment() {
    }

    @Override
    public  View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_main, container, false);
        relativeLayout=(RelativeLayout)root.findViewById(R.id.content_main);

//-----------------------------------------------------------------------------------------
// permission
        Context c=getContext();
        ConnectivityManager comman= (ConnectivityManager) c.getSystemService(getContext().CONNECTIVITY_SERVICE);
          wifi=(Boolean) comman.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();

        gridView=(GridView)root.findViewById(R.id.gridview);
        if (wifi) {

            relativeLayout.setBackgroundColor(Color.BLUE);
        if (API_KEY.isEmpty()) {
            Toast.makeText(getContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return null;
        }

            APIInterface apiService = APIClient.getClient().create(APIInterface.class);
            Call<MovieResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                movies = response.body().getResults();
                gridView.setAdapter(new ImageAdapter(getContext(),movies));

                Log.d(TAG, "Number of movies received: " + movies.size());
            }
            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Try Again", Toast.LENGTH_LONG).show();
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

//============================================ when click on any poster

        }
        else {
           Toast.makeText(getContext(), "No Internet... Please obtain wifi or mobileData", Toast.LENGTH_LONG).show();
          relativeLayout.setBackgroundResource(R.drawable.wifi);
        }
//-----------------------------------------------------------------------------------------
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                movie item = (movie) adapterView.getItemAtPosition(i);
                Configuration conf= view.getResources().getConfiguration();
                if(conf.smallestScreenWidthDp>600) {
                    final detailsFragment F=new detailsFragment();
                   getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment2,F).commit();
                   Bundle B=new Bundle();
                    B.putParcelable("movie",item);
                  F.setArguments(B);
                }
                else {
                    Intent intent = new Intent(getActivity(), movie_detail.class);
                    intent.putExtra("movie", item);
                    startActivity(intent);
                }
            }
        });

        return  root;
    }


}
