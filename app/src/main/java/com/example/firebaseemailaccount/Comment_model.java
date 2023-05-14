package com.example.firebaseemailaccount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class Comment_model {
    @SerializedName("community")
    @Expose
    private Integer community_id;

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("view_count")
    @Expose
    private Integer view_count;

    @SerializedName("created_at")
    @Expose
    private Date created_at; // java.sql.Date -> yyyy-mm-dd 형식이어야 함

    public Comment_model(String content){
        this.content = content;
    }

    public Integer getId(){
        return id;
    }

    public String getContent(){
        return content;
    }
}