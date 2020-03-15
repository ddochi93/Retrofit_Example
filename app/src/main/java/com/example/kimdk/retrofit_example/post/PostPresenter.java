package com.example.kimdk.retrofit_example.post;

import android.util.Log;

import com.example.kimdk.retrofit_example.RetrofitFactory;
import com.example.kimdk.retrofit_example.RetrofitService;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostPresenter implements PostContract.Presenter {
    private PostContract.View view;

    public PostPresenter(PostContract.View view) {
        this.view = view;
    }

    @Override
    public void submitPost(String title, String content) {
        JsonObject object = new JsonObject();
        object.addProperty("title", title);
        object.addProperty("content", content);

        RetrofitService service = RetrofitFactory.create();
        service.postMemo(object).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("success", "suc");
                    view.finishPost();
                }
                //Log.d("success", response.body().toString());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("fail", t.toString());
            }
        });
    }
}
