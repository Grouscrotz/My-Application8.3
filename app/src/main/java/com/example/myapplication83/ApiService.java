package com.example.myapplication83;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("woof.json")
    Call<DogResponse> getRandomDog();
}

