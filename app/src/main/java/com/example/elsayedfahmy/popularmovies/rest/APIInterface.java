package com.example.elsayedfahmy.popularmovies.rest;
import com.example.elsayedfahmy.popularmovies.model.*;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by elsayedfahmy on 21/10/2016.
 */

public interface APIInterface {


    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
  Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);



    @GET("movie/{id}")
    Call<MovieResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);




}