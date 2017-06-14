package com.example.elsayedfahmy.popularmovies.Details;


import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.elsayedfahmy.popularmovies.SqlData.*;
import com.example.elsayedfahmy.popularmovies.adapter.*;
import com.example.elsayedfahmy.popularmovies.R;
import com.example.elsayedfahmy.popularmovies.SqlData.SqliteUtil;
import com.example.elsayedfahmy.popularmovies.adapter.trailerAdapter;
import com.example.elsayedfahmy.popularmovies.model.*;
import com.example.elsayedfahmy.popularmovies.rest.*;
import com.example.elsayedfahmy.popularmovies.trailermodel.tarilerresponse;
import com.example.elsayedfahmy.popularmovies.trailermodel.trailer;
import com.example.elsayedfahmy.popularmovies.reviewsModel.*;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class detailsFragment extends android.support.v4.app.Fragment {


    public detailsFragment() {
        // Required empty public constructor
    }
    String API_KEY = "83900e399daa5d56e8aaefb7871cf094";
    String Base_URL = "https://image.tmdb.org/t/p/w500";
    List<trailer> trailermovies;
    List<reviews> reviewsesmovies;
    GridView gridView2;
    GridView gridView3;
    ListView listView_trailer;
    List<movie> movies=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root = inflater.inflate(R.layout.fragment_details, container, false);

        final movie movie;
        Configuration conf = root.getResources().getConfiguration();
        if (conf.smallestScreenWidthDp > 600) {
            Bundle sentbundle = getArguments();
            movie = sentbundle.getParcelable("movie");
        } else {
            movie = getActivity().getIntent().getExtras().getParcelable("movie");
        }
        getActivity().setTitle(movie.getTitle());
        if (movie == null) {
            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            return null;
        }

        ImageView Poster = (ImageView) root.findViewById(R.id.Poster_Image);
        TextView Title = (TextView) root.findViewById(R.id.txt_movie_name);
        TextView Vote = (TextView) root.findViewById(R.id.txt_movie_vote);
        TextView ReleaseDate = (TextView) root.findViewById(R.id.txt_release_date);
        TextView Description = (TextView) root.findViewById(R.id.txt_overview);
        final ListView list_reviews = (ListView) root.findViewById(R.id.list_reviews);
        final Button btnfavorite = (Button) root.findViewById(R.id.btnfavorite);


        //================================================================================================


        SqliteUtil util = new SqliteUtil(getContext());
        final SQLiteDatabase sqLiteDatabase1 = util.getWritableDatabase();
        String columns[] =
                {movie_data_favorite.KEY_ID,
                        movie_data_favorite.path,
                        movie_data_favorite.movie_id
                };

        Cursor c = sqLiteDatabase1.query(movie_data_favorite.TABLE_NAME, columns, null, null, null, null, movie_data_favorite.KEY_ID);
        if (c.moveToFirst()) {
            do {

                int m = c.getInt(c.getColumnIndex(movie_data_favorite.KEY_ID));
                String path = c.getString(c.getColumnIndex(movie_data_favorite.path));
                int movie_id = c.getInt(c.getColumnIndex(movie_data_favorite.movie_id));
                movies.add(new movie(movie_id));

            } while (c.moveToNext());
        }
        //=========== to check if movie in favorite or no to set the text of button
        int id=movie.getId();
        for (int i = 0; i < movies.size(); i++) {

            if (movies.get(i).getId()==id) {
                btnfavorite.setText("Delete From Favorite");
                break;
            }
        }

            btnfavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


//=======================================
                    if(btnfavorite.getText()=="Delete From Favorite")
                    {
                       //================================ to delate movie from favorite
                        // Define 'where' part of query.
                        String selection = movie_data_favorite.title + " LIKE ?";
                        // Specify arguments in placeholder order.
                        String title=movie.getTitle().toString();
                        String []whereargs={title};
                        // Issue SQL statement.
                        sqLiteDatabase1.delete(movie_data_favorite.TABLE_NAME, selection, whereargs);
                        Toast.makeText(getContext(), "Deleted", Toast.LENGTH_LONG).show();
                        btnfavorite.setText("Make As Favorite");


                    }
                    else
                    {
                   //--------------------------
 //=============== to refresh fata in favorite if user clicked delete data befor make as favorite
                        String columns[] =
                                {movie_data_favorite.KEY_ID,
                                        movie_data_favorite.path,
                                        movie_data_favorite.movie_id
                                };

                        Cursor c = sqLiteDatabase1.query(movie_data_favorite.TABLE_NAME, columns, null, null, null, null, movie_data_favorite.KEY_ID);
                        if (c.moveToFirst()) {
                            do {

                                int m = c.getInt(c.getColumnIndex(movie_data_favorite.KEY_ID));
                                String path = c.getString(c.getColumnIndex(movie_data_favorite.path));
                                int movie_id = c.getInt(c.getColumnIndex(movie_data_favorite.movie_id));
                                movies.add(new movie(movie_id));

                            } while (c.moveToNext());
                        }


         //=========== to check if movie in favorite or no
                        int id=movie.getId();
                        int y=0;
                        for (int i = 0; i < movies.size(); i++) {

                            if (movies.get(i).getId()==id) {

                                y =1;
                                break;
                            }
                        }

                        if (y==1)
                        {
                            Toast.makeText(getContext(),"its favorite ", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            //===========  add dara to database
                            SqliteUtil sqliteUtil = new SqliteUtil(getContext());
                            SQLiteDatabase sqLiteDatabase = sqliteUtil.getWritableDatabase();
                            ContentValues contentValues = new ContentValues();
                            contentValues.put(movie_data_favorite.path, movie.getPosterPath());
                            contentValues.put(movie_data_favorite.title, movie.getTitle());
                            contentValues.put(movie_data_favorite.vote_average, movie.getVoteAverage());
                            contentValues.put(movie_data_favorite.releaseDate, movie.getReleaseDate());
                            contentValues.put(movie_data_favorite.original_title, movie.getOriginalTitle());
                            contentValues.put(movie_data_favorite.original_language, movie.getOriginalLanguage());
                            contentValues.put(movie_data_favorite.backdrop_path, movie.getBackdropPath());
                            contentValues.put(movie_data_favorite.vote_count, movie.getVoteCount());
                            int check;
                            if (movie.isAdult()) {
                                check = 1;
                            } else check = 0;
                            contentValues.put(movie_data_favorite.adult, check);
                            int v;
                            if (movie.getVideo()) {
                                v = 1;
                            } else v = 0;
                            contentValues.put(movie_data_favorite.video, v);
                            contentValues.put(movie_data_favorite.popularity, movie.getPopularity());
                            contentValues.put(movie_data_favorite.movie_id, movie.getId());
                            contentValues.put(movie_data_favorite.description, movie.getOverview());


                            long row = sqLiteDatabase.insert(movie_data_favorite.TABLE_NAME, null, contentValues);
                            if (row > 0) {
                                Toast.makeText(getContext(),movie.getTitle()+" added to Your Favorite List", Toast.LENGTH_LONG).show();
                                btnfavorite.setText("Delete From Favorite");
                            } else {
                                Toast.makeText(getContext(), "No", Toast.LENGTH_LONG).show();
                            }

                        }
                    }

                }
            });

//=========================Image==================================
            String Path = Base_URL + movie.getPosterPath();
            Picasso.with(getContext()).load(Path).into(Poster);
//=================================================================
            Title.setText(movie.getTitle());
            Vote.setText(movie.getVoteAverage() + "");
            ReleaseDate.setText(movie.getReleaseDate());
            Description.setText(movie.getOverview());

//============================================== bring movies trailers
        gridView2 = (GridView) root.findViewById(R.id.gridview2);
            try {
                final trailerInterface trailerService = trailerClient.getClient().create(trailerInterface.class);
                Call<tarilerresponse> call = trailerService.getMoviesTrailers(movie.getId(), API_KEY);
                call.enqueue(new Callback<tarilerresponse>() {
                    @Override
                    public void onResponse(Call<tarilerresponse> call, Response<tarilerresponse> response) {
                        trailermovies = response.body().getResults();
                        gridView2.setAdapter(new trailerAdapter(getActivity(), trailermovies));

                    }


                    @Override
                    public void onFailure(Call<tarilerresponse> call, Throwable t) {
                       // Toast.makeText(getContext(), "fail", Toast.LENGTH_LONG).show();
                        TextView trailer=(TextView)root.findViewById(R.id.trailer);
                        trailer.setText("Trailer :  NoTrailer (check the internet)");
                        // Log error here since request failed
                        //Log.e(TAG, t.toString());
                    }
                });
            } catch (Exception e) {

            }

        //================================== bring movies reviews


            final reviewsInterface reviewsService = reviewsClient.getClient().create(reviewsInterface.class);
            Call<reviewsResponse> call2 = reviewsService.getMoviesReviews(movie.getId(), API_KEY);
        call2.enqueue(new Callback<reviewsResponse>() {
                @Override
                public void onResponse(Call<reviewsResponse> call, Response<reviewsResponse> response) {
                    reviewsesmovies = response.body().getResults();
                    //gridView3.setAdapter(new reviewsAdapter(getContext(),reviewsesmovies));
                    list_reviews.setAdapter(new reviewsAdapter(getContext(), reviewsesmovies));

                }


                @Override
                public void onFailure(Call<reviewsResponse> call2, Throwable t) {

                    //Toast.makeText(getContext(), "fail reviews ", Toast.LENGTH_LONG).show();
                    TextView reviews=(TextView)root.findViewById(R.id.txt_reviews);
                    reviews.setText("Reviews:  NoReviews (check the internet)");

                }
            });

            //====================================
            gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    // to watch video trailer on internet
                    trailer item = (trailer) adapterView.getItemAtPosition(i);
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + item.getKey())));
                }
            });

            return root;
        }

    @Override
    public void onResume() {
        super.onResume();
        this.onCreate(null);
    }
}

