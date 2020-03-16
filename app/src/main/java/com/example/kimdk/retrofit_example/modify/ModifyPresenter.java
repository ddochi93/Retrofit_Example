package com.example.kimdk.retrofit_example.modify;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.kimdk.retrofit_example.RetrofitFactory;
import com.example.kimdk.retrofit_example.RetrofitService;
import com.google.gson.JsonObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ModifyPresenter implements ModifyContract.Presenter {
    private ModifyContract.View view;

    public ModifyPresenter( ModifyContract.View view) {
        this.view = view;
    }


    @SuppressLint("CheckResult")
    @Override
    public void submitModify(int id, String title, String content) {
        JsonObject object = new JsonObject();
        object.addProperty("id",id);
        object.addProperty("title",title);
        object.addProperty("content",content);

        RetrofitService service = RetrofitFactory.create();
        service.updateMemo(id, object)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(memobean -> {view.finishModify();}
                , throwable -> {Log.e("fail",throwable.toString());
                });
    }
}
