package com.example.kimdk.retrofit_example.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kimdk.retrofit_example.data.Memobean;
import com.example.kimdk.retrofit_example.post.PostActivity;
import com.example.kimdk.retrofit_example.R;

import java.util.List;


public class MainActivity extends AppCompatActivity implements MainContract.View {
    private MainContract.Presenter presenter;
    RecyclerView recyclerView;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab25_3);

        recyclerView = findViewById(R.id.lab3_list);
        addBtn = findViewById(R.id.addBtn);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PostActivity.class);
                startActivityForResult(intent,4000);  //4000의미 있나?
            }
        });


        presenter = new MainPresenter();
        presenter.setView(this);
        presenter.getMemoList();
    }

    @Override
    public void updateView(List<Memobean> memoList) {
        MyAdapter adapter = new MyAdapter(memoList, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void refreshView() {
        presenter.getMemoList();
    }

    @Override
    public void toastMessage(String message) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d("result","result : " + resultCode + ", resquest : " + requestCode);
        super.onActivityResult(requestCode, resultCode, data);
        presenter.getMemoList();
    }
}


