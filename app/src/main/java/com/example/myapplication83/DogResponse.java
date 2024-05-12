package com.example.myapplication83;
import com.google.gson.annotations.SerializedName;

public class DogResponse {

    @SerializedName("url")
    private String url;

    public String getUrl() {
        return url;
    }
}

