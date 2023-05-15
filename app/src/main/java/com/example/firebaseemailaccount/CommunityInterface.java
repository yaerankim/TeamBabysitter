package com.example.firebaseemailaccount;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Body;

public interface CommunityInterface {
    @POST("community/create/") // 게시물 upload
    Call<Community_model> community_post(@Body Community_model post);

    @GET("community/{id}") // 특정 게시물 get
    Call<Community_model> community_detail_get(@Path("id") Integer id);

    @GET("community/") // 전체 게시물 list get
    Call<Community_model> community_get();

    @POST("comment/create/{id}") // 지정한 게시물 댓글 upload
    Call<Comment_model> comment_post(@Path("id") Integer id);

    // path('comment/<int:pk>', CommentDetail.as_view(), name='comment-detail'),
}