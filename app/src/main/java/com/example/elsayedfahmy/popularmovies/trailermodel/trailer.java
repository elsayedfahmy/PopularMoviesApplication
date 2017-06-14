package com.example.elsayedfahmy.popularmovies.trailermodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by elsayedfahmy on 10/11/2016.
 */

public class trailer {
    @SerializedName("name")
    private String name;
    @SerializedName("key")
    private String key;

    public trailer(String name,String key)
    {
        this.name=name;
        this.key=key;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKey(String key) {
        this.key = key;
    }



    public String getKey() {
        return key;
    }



}
