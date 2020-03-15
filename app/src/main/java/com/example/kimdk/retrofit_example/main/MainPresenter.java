package com.example.kimdk.retrofit_example.main;

import android.util.Log;

import com.example.kimdk.retrofit_example.Memobean;
import com.example.kimdk.retrofit_example.RetrofitFactory;
import com.example.kimdk.retrofit_example.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements  MainContract.Presenter {
    private MainContract.View view;

    @Override
    public void setView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void getMemoList() {
        RetrofitService networkService = RetrofitFactory.create();
        networkService.getMemo().enqueue(new Callback<List<Memobean>>() {
            @Override
            public void onResponse(Call<List<Memobean>> call, Response<List<Memobean>> response) {
                if(response.isSuccessful()) {
                    List<Memobean> res = response.body();
                    view.updateView(res);    //model을 view로 넘김.
                }
            }

            @Override
            public void onFailure(Call<List<Memobean>> call, Throwable t) {
                //t.printStackTrace();
                Log.e("eoroor","occured");
            }
        });
    }
//    RetrofitService networkService = RetrofitFactory.create();
//        networkService.getMemo().enqueue(new Callback<List<Memobean>>() {
//        @Override
//        public void onResponse(Call<List< Memobean >> call, Response<List<Memobean>> response) {
//            if(response.isSuccessful()) {
//                List<Memobean> res = response.body();
//                for(Memobean m : res) {
//                    Log.e("eoroor",m.getTitle());
//                }
//                MyAdapter adapter = new MyAdapter(response.body());
//                recyclerView.setAdapter(adapter);
//            }
//        }
//
//        @Override
//        public void onFailure(Call<List<Memobean>> call, Throwable t) {
//            //t.printStackTrace();
//            Log.e("eoroor","occured");
//        }
//    });
}
