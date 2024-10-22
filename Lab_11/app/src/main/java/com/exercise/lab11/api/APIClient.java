package com.exercise.lab11.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// API client to handle the base URL and retrofit instance
public class APIClient {
    private static String BASE_URL = "https://67174629b910c6a6e027403b.mockapi.io/";
    private static Retrofit retrofit;

    // Method to get the retrofit instance (create it if null)
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
