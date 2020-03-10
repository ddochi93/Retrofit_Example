package com.example.kimdk.retrofit_example;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
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


}
