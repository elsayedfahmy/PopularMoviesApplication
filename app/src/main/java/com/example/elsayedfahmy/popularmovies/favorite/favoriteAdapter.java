package com.example.elsayedfahmy.popularmovies.favorite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.example.elsayedfahmy.popularmovies.adapter.MovieViewHolder;
import com.example.elsayedfahmy.popularmovies.R;
import com.example.elsayedfahmy.popularmovies.adapter.MovieViewHolder;
import com.example.elsayedfahmy.popularmovies.adapter.MovieViewHolder;
import com.example.elsayedfahmy.popularmovies.adapter.ImageAdapter;
import com.example.elsayedfahmy.popularmovies.model.movie;
import com.example.elsayedfahmy.popularmovies.SqlData.*;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by elsayedfahmy on 04/11/2016.
 */

public class favoriteAdapter extends BaseAdapter {
    Context c;
    List<Movies> movies=null;
    public favoriteAdapter(Context C, List<Movies> Data)
    {
        this.c=C;
        movies=Data;

    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int i) {
        return movies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    String Base_URL="https://image.tmdb.org/t/p/w500";
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row=view;
        MovieViewHolder holder=null;
        if(row==null)
        {
            //call it for the only first time
           LayoutInflater inflater= (LayoutInflater) c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
            //bring the xml file here
            row=inflater.inflate(R.layout.item_style,viewGroup,false);
            holder=new MovieViewHolder(row);
            row.setTag(holder);
        }
        else
        {
            holder= (MovieViewHolder) row.getTag();
        }

        Movies m= (Movies) getItem(i);

//================================Download Poster Image Using Picasso=======================================

        String Poster=Base_URL+m.getPath();
        Picasso.with(c).load(Poster).into(holder.moviePoster);
        String p=m.getPath();



        return row;
    }
}
