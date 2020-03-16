package com.example.kimdk.retrofit_example.post;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.kimdk.retrofit_example.RetrofitFactory;
import com.example.kimdk.retrofit_example.RetrofitService;
import com.google.gson.JsonObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PostPresenter implements PostContract.Presenter {
    private PostContract.View view;

    public PostPresenter(PostContract.View view) {
        this.view = view;
    }

    @SuppressLint("CheckResult")
    @Override
    public void submitPost(String title, String content) {
        JsonObject object = new JsonObject();
        object.addProperty("title", title);
        object.addProperty("content", content);

        RetrofitService service = RetrofitFactory.create();
        service.postMemo(object)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aVoid -> {
                            view.finishPost();
                        }
                        , throwable -> {
                            Log.e("fail", throwable.toString());
                        });
    }
}
