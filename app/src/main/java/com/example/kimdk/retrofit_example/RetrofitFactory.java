package com.example.kimdk.retrofit_example;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static String BASE_URL = "https://newsapi.org";

//    static Retrofit getRetrofit() {
//        return new Retrofit.Builder()
//                .baseUrl("https:/ddd")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//    }

    public static RetrofitService create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RetrofitService.class);
    }
}
