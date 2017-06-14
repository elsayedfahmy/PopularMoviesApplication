package com.example.elsayedfahmy.popularmovies.rest;

import com.example.elsayedfahmy.popularmovies.trailermodel.tarilerresponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by elsayedfahmy on 10/11/2016.
 */

public interface trailerInterface {

    @GET("movie/{id}/videos")
    Call<tarilerresponse> getMoviesTrailers(@Path("id") int id, @Query("api_key") String apiKey);


}
