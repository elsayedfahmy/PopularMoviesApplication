package com.example.elsayedfahmy.popularmovies.reviewsModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by elsayedfahmy on 11/11/2016.
 */

public class reviews {
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    @SerializedName("author")
    private String author;
    @SerializedName("content")
    private String content;
    public String getContent() {

        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public reviews()
    {

    }
    public reviews(String content)
    {
        this.content=content;

    }
    public reviews(String author,String content)
    {
        this.author=author;
        this.content=content;
    }
}
