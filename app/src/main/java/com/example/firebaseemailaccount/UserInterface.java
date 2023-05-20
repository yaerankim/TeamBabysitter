package com.example.firebaseemailaccount;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserInterface {
    @POST("user/register/") // user 회원가입
    Call<UserAccount> user_register(@Body UserAccount user);

    @POST("user/login/") // user 로그인
    Call<UserAccount> user_login(@Body UserAccount user);
}
