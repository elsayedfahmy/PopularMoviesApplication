package com.example.elsayedfahmy.popularmovies.SqlData;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by elsayedfahmy on 04/11/2016.
 */

public class SqliteUtil extends SQLiteOpenHelper {
    public SqliteUtil(Context context) {
        super(context, movie_data_favorite.DB_NAME, null, movie_data_favorite.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "create table "+movie_data_favorite.TABLE_NAME+" ("+movie_data_favorite.KEY_ID+" integer primary key autoincrement,"
                +movie_data_favorite.path+" text, "+
                movie_data_favorite.title+" text, " +
                movie_data_favorite.vote_average+" text, " +
                movie_data_favorite.releaseDate+" text, "+
                movie_data_favorite.original_title+" text, " +
                movie_data_favorite.original_language+" text, "+
                movie_data_favorite.backdrop_path+" text, " +
                movie_data_favorite.vote_count+" INTEGER, " +
               movie_data_favorite.adult+" NUMERIC, "+
               movie_data_favorite.video+" NUMERIC, " +
                movie_data_favorite.popularity+" REAL, " +
                movie_data_favorite.movie_id+" text, " +
                movie_data_favorite.description+" text)";


        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "drop table "+movie_data_favorite.TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
}
