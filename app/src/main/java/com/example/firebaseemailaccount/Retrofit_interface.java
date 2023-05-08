package com.example.firebaseemailaccount;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Retrofit_interface {
    @GET("community/{id}")
    Call<Data_model> test_api_get(
            @Path("id") Integer id);
}
