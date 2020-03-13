package com.example.kimdk.retrofit_example;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitService {
//    @GET("/v2/everything")
//    Call<PageListModel> getList(@Query("q")String q,
//                                @Query("apiKey")String apiKey,
//                                @Query("page")long page,
//                                @Query("pageSize")int pageSize);

    @GET("/memo")
    Call<List<Memobean>> getMemo();

    @DELETE("/memo/{id}")
    Call<Void> delMemo(@Path("id") int id);


    @PUT("/memo/{id}")
    Call<Memobean> updateMemo(@Path("id") int id,@Body JsonObject body);

    @POST("/memo")
    Call<Void> postMemo(@Body JsonObject memo);

}
