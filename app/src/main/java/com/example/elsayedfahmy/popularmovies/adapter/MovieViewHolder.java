package com.example.elsayedfahmy.popularmovies.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import  com.example.elsayedfahmy.popularmovies.R;
/**
 * Created by elsayedfahmy on 22/10/2016.
 */

public class MovieViewHolder {

    //TextView movieTitle;
  public   ImageView moviePoster;


    public MovieViewHolder(View v) {
      //  movieTitle = (TextView) v.findViewById(R.id.Movie_Name);
        moviePoster=(ImageView)v.findViewById(R.id.Movie_Image);
    }

}
