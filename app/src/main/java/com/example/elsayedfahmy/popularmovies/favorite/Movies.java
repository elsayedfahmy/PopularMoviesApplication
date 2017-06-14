package com.example.elsayedfahmy.popularmovies.favorite;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by elsayedfahmy on 04/11/2016.
 */

public class Movies implements Parcelable {


    protected Movies(Parcel in) {
        path = in.readString();
        title = in.readString();
        vote = in.readString();
        releaseDate = in.readString();
        description = in.readString();
        original_title = in.readString();
        original_language = in.readString();
        backdrop_path = in.readString();
        vote_average = in.readString();
        video = in.readByte() != 0;
    }

    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel in) {
            return new Movies(in);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };

    public String getPath() {
        return path;
    }

    public String getTitle() {
        return title;
    }

    public String getVote() {
        return vote;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getAdult() {
        return adult;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public Double getPopularity() {
        return popularity;
    }

    public Integer getVote_count() {
        return vote_count;
    }

    public String getVote_average() {
        return vote_average;
    }

    public boolean isVideo() {
        return video;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public void setVote_count(Integer vote_count) {
        this.vote_count = vote_count;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    String path ;
    String title ;
    String vote ;
     String releaseDate ;
     String description ;
    Boolean adult;
     String original_title;
     String original_language;
     String backdrop_path ;
     Double popularity;
     Integer vote_count;
     String vote_average;
    //final boolean video=movie.getVideo();
     boolean video;

    public Movies() {
    }

    public Movies(String posterPath, Boolean adult, String overview, String releaseDate,
                 String originalTitle, String originalLanguage, String title, String backdropPath
            , Boolean video, String voteAverage,int voteCount,double popularity) {
        this.path = posterPath;
        this.adult = adult;
        this.description = overview;
        this.releaseDate = releaseDate;
        this.original_title = originalTitle;
        this.original_language = originalLanguage;
        this.title = title;
        this.backdrop_path = backdropPath;
        this.popularity=popularity;
        this.vote_count = voteCount;
        this.video = video;
        this.vote_average = voteAverage;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(path);
        parcel.writeString(title);
        parcel.writeString(vote);
        parcel.writeString(releaseDate);
        parcel.writeString(description);
        parcel.writeString(original_title);
        parcel.writeString(original_language);
        parcel.writeString(backdrop_path);
        parcel.writeString(vote_average);
        parcel.writeByte((byte) (video ? 1 : 0));
    }
}
