package com.example.firebaseemailaccount;

import retrofit2.Call;

public class ListViewItem {
    private String contentStr;
    private String titleStr;

    public void setItemTitle(String title) {
        titleStr = title;
    }

    public void setItemContent(String content) {
        contentStr = content;
    }

    public String getItemContent() {
        return this.contentStr;
    }

    public String getItemTitle() {
        return this.titleStr;
    }
}
