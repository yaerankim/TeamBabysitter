package com.example.firebaseemailaccount;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit_client {
    private static final String BASE_URL = "https://3ef3-223-194-128-179.ngrok-free.app/";


    public static CommunityInterface getApiService(){return getInstance().create(CommunityInterface.class);}

    private static Retrofit getInstance(){
        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
