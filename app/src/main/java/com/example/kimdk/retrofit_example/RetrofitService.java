package com.example.kimdk.retrofit_example;

import com.example.kimdk.retrofit_example.data.Memobean;
import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitService {

    @GET("/memo")
    Observable<List<Memobean>> getMemo();

    @DELETE("/memo/{id}")
    Observable<Response<Void>> delMemo(@Path("id") int id);


    @PUT("/memo/{id}")
    Observable<Memobean> updateMemo(@Path("id") int id,@Body JsonObject body);

    @POST("/memo")
    Observable<Response<Void>> postMemo(@Body JsonObject memo);

}
