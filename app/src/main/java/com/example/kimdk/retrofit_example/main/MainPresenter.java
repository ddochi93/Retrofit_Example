package com.example.kimdk.retrofit_example.main;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.kimdk.retrofit_example.RetrofitFactory;
import com.example.kimdk.retrofit_example.RetrofitService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;

    @Override
    public void setView(MainContract.View view) {
        this.view = view;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getMemoList() {
        RetrofitService networkService = RetrofitFactory.create();

        networkService.getMemo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(memobeans -> {
                            view.updateView(memobeans);
                        }
                        , throwable -> {
                            Log.e("fail", throwable.toString());
                        });
    }

}
