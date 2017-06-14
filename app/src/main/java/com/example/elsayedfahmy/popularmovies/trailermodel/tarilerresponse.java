package com.example.elsayedfahmy.popularmovies.trailermodel;

import com.example.elsayedfahmy.popularmovies.model.*;
import com.example.elsayedfahmy.popularmovies.trailermodel.trailer;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by elsayedfahmy on 10/11/2016.
 */

public class tarilerresponse {
    @SerializedName("results")
    private List<trailer> results;

    public List<trailer> getResults() {
        return results;
    }

    public void setResults(List<trailer> results) {
        this.results = results;
    }



}
