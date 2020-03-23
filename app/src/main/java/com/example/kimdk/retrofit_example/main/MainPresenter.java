package com.example.kimdk.retrofit_example.main;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.kimdk.retrofit_example.RetrofitFactory;
import com.example.kimdk.retrofit_example.RetrofitService;
import com.example.kimdk.retrofit_example.main.adapter.MainAdapterContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;
    private MainAdapterContract.View mainAdapterView;
    private MainAdapterContract.Model mainAdapterModel;

    public MainPresenter(MainContract.View view) {
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

    @SuppressLint("CheckResult")
    @Override
    public void removeFromMemoList(int id, int position) {
        RetrofitService retrofitService = RetrofitFactory.create();

        retrofitService.delMemo(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aVoid -> {
                    Log.d("succeeeed", "Good");
                    //view.removeItemFromAdapter(position);
                    mainAdapterModel.removeItem(position);
                    mainAdapterView.notifyAdapter(position);
                }, throwable -> Log.e("fail", throwable.toString()));

    }

    @Override
    public void setMainAdapterModel(MainAdapterContract.Model mainAdapterModel) {
        this.mainAdapterModel = mainAdapterModel;
    }

    @Override
    public void setMainAdapterView(MainAdapterContract.View mainAdapterView) {
        this.mainAdapterView = mainAdapterView;
    }

}
