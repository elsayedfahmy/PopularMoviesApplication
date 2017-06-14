package com.example.elsayedfahmy.popularmovies.rest;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by elsayedfahmy on 21/10/2016.
 */
//===========To send network requests to an API, we need to use the Retrofit
// Builder class and specify the base URL for the service.
// So, create a class named ApiClient.java under rest package


public class APIClient {

    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
