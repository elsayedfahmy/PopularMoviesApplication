package com.example.elsayedfahmy.popularmovies.SqlData;

import java.util.List;

/**
 * Created by elsayedfahmy on 04/11/2016.
 */

public class movie_data_favorite {

   public static final String DB_NAME="FavoriteMovie_db";
   public static final String TABLE_NAME="my_FavoriteMovie";

   //columns
   public static final String KEY_ID="_id";
   public static final String path ="movie_path";
   public static final String title ="movie_title";
   public static final String releaseDate ="movie_ReleaseDate";
   public static final String description = "movie_Description";
   public static final String adult = "movie_adult";
   public static final String original_title = "movie_original_title";
   public static final String original_language = "movie_original_language";
   public static final String backdrop_path = "movie_backdrop_path";
   public static final String popularity ="movie_popularity";
   public static final String vote_count = "movie_vote_count";
   public static final String video = "movie_video";
   public static final String vote_average = "movie_vote_average";
   public static final String movie_id = "movie_movie_id";


   //DB Version
   public static final int DB_VERSION = 19;



}
