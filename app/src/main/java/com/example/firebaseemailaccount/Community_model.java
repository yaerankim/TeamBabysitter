package com.example.firebaseemailaccount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class Community_model {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("view_count")
    @Expose
    private int view_count;

    @SerializedName("created_at")
    @Expose
    private Date created_at; // java.sql.Date -> yyyy-mm-dd 형식이어야 함

    @SerializedName("row_count")
    @Expose
    private int row_count;

    public Community_model(String title, String content){
        this.title = title;
        this.content = content;
    }

//    public Community_model getCommunityObject(int id) {
//        this.getClass();
//        return this;
//    }

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    // ?
//    public String getTitleById(int id){
//        return title;
//    }

    public String getContent(){
        return content;
    }

    public int getViewCount(){
        return view_count;
    }

    public Date getCreatedAt(){
        return created_at;
    }

    public int getRowCount(){
        return row_count;
    }
}