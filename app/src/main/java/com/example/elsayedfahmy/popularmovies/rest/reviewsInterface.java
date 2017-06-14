package com.example.elsayedfahmy.popularmovies.rest;

import com.example.elsayedfahmy.popularmovies.trailermodel.tarilerresponse;
import com.example.elsayedfahmy.popularmovies.reviewsModel.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by elsayedfahmy on 11/11/2016.
 */

public interface reviewsInterface {
    @GET("movie/{id}/reviews")
    Call<reviewsResponse> getMoviesReviews(@Path("id") int id, @Query("api_key") String apiKey);
}
