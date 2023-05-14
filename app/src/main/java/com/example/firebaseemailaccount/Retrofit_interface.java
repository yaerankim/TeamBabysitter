package com.example.firebaseemailaccount;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Retrofit_interface {
    @GET("community/{id}")
    Call<Data_model> test_api_get(
            @Path("id") Integer id);

    @POST("community/create/")
    Call<Data_model> community_post(@Body Data_model post);
}
