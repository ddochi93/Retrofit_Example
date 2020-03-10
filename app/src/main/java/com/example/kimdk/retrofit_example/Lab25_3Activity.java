package com.example.kimdk.retrofit_example;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Lab25_3Activity extends AppCompatActivity {

    private static final String QUERY = "apple";
    private static final String API_KEY = "199df47a83e64f91b778bc96b9b12ab8";

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab25_3);
        recyclerView = findViewById(R.id.lab3_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        RetrofitService networkService = RetrofitFactory.create();
//        networkService.getList(QUERY, API_KEY, 1, 10)
//                .enqueue(new Callback<PageListModel>() {
//                    @Override
//                    public void onResponse(Call<PageListModel> call, Response<PageListModel> response) {
//                        if(response.isSuccessful()){
//                            MyAdapter adapter = new MyAdapter(response.body().articles);
//                            recyclerView.setAdapter(adapter);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<PageListModel> call, Throwable t) {
//                        t.printStackTrace();
//                    }
//                });
        networkService.getMemo().enqueue(new Callback<List<Memobean>>() {
            @Override
            public void onResponse(Call<List<Memobean>> call, Response<List<Memobean>> response) {
                if(response.isSuccessful()) {
                    List<Memobean> res = response.body();
                    for(Memobean m : res) {
                        Log.e("eoroor",m.getTitle());
                    }
                    MyAdapter adapter = new MyAdapter(response.body());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Memobean>> call, Throwable t) {
                //t.printStackTrace();
                Log.e("eoroor","occured");
            }
        });


    }
}


