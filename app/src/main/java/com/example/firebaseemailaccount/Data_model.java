package com.example.firebaseemailaccount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data_model {
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

    public Data_model(String title, String content){
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

    public Integer getViewCount(){
        return view_count;
    }
}
