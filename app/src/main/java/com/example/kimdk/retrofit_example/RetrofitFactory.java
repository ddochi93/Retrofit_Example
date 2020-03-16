package com.example.kimdk.retrofit_example;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static String BASE_URL = "http://ec2-52-79-156-63.ap-northeast-2.compute.amazonaws.com:8080/";

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
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(RetrofitService.class);
    }


}
