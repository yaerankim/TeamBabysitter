package com.example.firebaseemailaccount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class Community_model {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("view_count")
    @Expose
    private Integer view_count;

    @SerializedName("created_at")
    @Expose
    private Date created_at; // java.sql.Date -> yyyy-mm-dd 형식이어야 함

    public Community_model(String title, String content){
        this.title = title;
        this.content = content;
    }

    public Integer getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }
}