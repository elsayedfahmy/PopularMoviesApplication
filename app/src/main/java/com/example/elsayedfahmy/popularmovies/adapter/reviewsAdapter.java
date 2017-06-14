package com.example.elsayedfahmy.popularmovies.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elsayedfahmy.popularmovies.R;
import com.example.elsayedfahmy.popularmovies.model.movie;
import com.example.elsayedfahmy.popularmovies.reviewsModel.*;
import com.example.elsayedfahmy.popularmovies.trailermodel.trailer;

import java.util.List;

/**
 * Created by elsayedfahmy on 17/11/2016.
 */

public class reviewsAdapter extends BaseAdapter {

public class holder
{

  public   TextView itemauthor;
   public TextView content;
    public holder(View v) {
        itemauthor = (TextView) v.findViewById(R.id.itemauther);
        content = (TextView) v.findViewById(R.id.itemcontent);
    }
}
    List<reviews> movieReview;
    Context context;
    public  reviewsAdapter(Context C,List<reviews> Data)
    {
        this.context=C;
        movieReview=Data;
    }
    @Override
    public int getCount() {
        return movieReview.size();
    }

    @Override
    public Object getItem(int position) {
        return movieReview.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    holder  holder;
    @Override
    public View getView(int i, View v, ViewGroup parent) {
        if(v==null)
        {
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.review_item,parent,false);
            holder=new holder(v);
            v.setTag(holder);
        }

        reviews R=(reviews) getItem(i);
if(R.getAuthor()==null || R.getContent()==null)
{
    holder.itemauthor.setText("No overview");
}else {
    holder.itemauthor.setText(R.getAuthor() + " :");
    holder.content.setText(R.getContent());
}
        return v;
    }




}
