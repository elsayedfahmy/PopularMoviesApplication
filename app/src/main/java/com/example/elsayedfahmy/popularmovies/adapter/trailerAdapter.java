package com.example.elsayedfahmy.popularmovies.adapter;
import com.example.elsayedfahmy.popularmovies.R;
import com.example.elsayedfahmy.popularmovies.model.*;
import com.example.elsayedfahmy.popularmovies.model.*;
import com.squareup.picasso.Picasso;
import com.example.elsayedfahmy.popularmovies.model.*;
import com.example.elsayedfahmy.popularmovies.trailermodel.*;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by elsayedfahmy on 10/11/2016.
 */


public class trailerAdapter extends BaseAdapter {

    Context C;
    List<trailer> movies=null;
    public trailerAdapter(Context C, List<trailer> Data)
    {
        this.C=C;
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

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View row=view;
        MovieViewHolder holder;
        if(row==null)
        {
            //call it for the only first time
            LayoutInflater inflater= (LayoutInflater) C.getSystemService(C.LAYOUT_INFLATER_SERVICE);
            //bring the xml file here
            row=inflater.inflate(R.layout.item_style,viewGroup,false);
            holder=new MovieViewHolder(row);
            row.setTag(holder);
        }
        else
        {
            holder= (MovieViewHolder) row.getTag();
        }
       trailer m= (trailer) getItem(position);

//================================Download Poster Image Using Picasso=======================================
        String key=m.getKey();
       String TrailerImageUrl = "http://img.youtube.com/vi/"+key+"/default.jpg";
       Picasso.with(C).load(TrailerImageUrl).into(holder.moviePoster);


        return row;






    }
}
