package com.example.elsayedfahmy.popularmovies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elsayedfahmy.popularmovies.model.movie;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

import com.example.elsayedfahmy.popularmovies.R;
/**
 * Created by elsayedfahmy on 21/10/2016.
 */

public class ImageAdapter extends BaseAdapter {

    String Base_URL="https://image.tmdb.org/t/p/w500";
    Context C;
    List<movie> movies=null;
    public ImageAdapter(Context C, List<movie> Data)
    {
        this.C=C;
        movies=Data;

    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        View row=v;
        MovieViewHolder holder=null;
        if(row==null)
        {
            //call it for the only first time
            LayoutInflater inflater= (LayoutInflater) C.getSystemService(C.LAYOUT_INFLATER_SERVICE);
            //bring the xml file here
            row=inflater.inflate(R.layout.item_style,parent,false);
            holder=new MovieViewHolder(row);
            row.setTag(holder);
        }
        else
        {
            holder= (MovieViewHolder) row.getTag();
        }

        movie m= (movie) getItem(position);

//================================Download Poster Image Using Picasso=======================================

        String Poster=Base_URL+m.getPosterPath();
        Picasso.with(C).load(Poster).into(holder.moviePoster);
        String p=m.getPosterPath();
        return row;
    }


}
