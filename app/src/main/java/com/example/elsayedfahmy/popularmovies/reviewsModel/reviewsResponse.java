package com.example.elsayedfahmy.popularmovies.reviewsModel;

import com.example.elsayedfahmy.popularmovies.trailermodel.trailer;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by elsayedfahmy on 11/11/2016.
 */

public class reviewsResponse {
    @SerializedName("results")
    private List<reviews> results;

    public List<reviews> getResults() {return results;}
    public void setResults(List<reviews> results) {
        this.results = results;
    }




}
