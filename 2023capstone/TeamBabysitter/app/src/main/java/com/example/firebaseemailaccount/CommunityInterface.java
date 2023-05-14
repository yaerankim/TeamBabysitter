package com.example.firebaseemailaccount;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Body;

public interface CommunityInterface {
    @POST("community/create/") // 마지막 slash 중요!
    Call<Community_model> community_post(@Body Community_model post);

    @GET("community/{id}")
    Call<Community_model> community_get(@Path("id") Integer id);
}