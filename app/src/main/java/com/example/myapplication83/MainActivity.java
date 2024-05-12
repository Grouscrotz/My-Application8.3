package com.example.myapplication83;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button btnLoadImage;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLoadImage = findViewById(R.id.btnLoadImage);
        imageView = findViewById(R.id.imageView);

        btnLoadImage.setOnClickListener(view -> loadImage());
    }

    private void loadImage() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://random.dog/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<DogResponse> call = apiService.getRandomDog();

        call.enqueue(new Callback<DogResponse>() {
            @Override
            public void onResponse(Call<DogResponse> call, Response<DogResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String imageUrl = response.body().getUrl();
                    Glide.with(MainActivity.this).load(imageUrl).into(imageView);
                }
            }

            @Override
            public void onFailure(Call<DogResponse> call, Throwable t) {
                // Обработка ошибки загрузки изображения
            }
        });
    }
}