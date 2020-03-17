package com.example.kimdk.retrofit_example.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.kimdk.retrofit_example.R;
import com.example.kimdk.retrofit_example.data.Memobean;
import com.example.kimdk.retrofit_example.databinding.ActivityMainBinding;
import com.example.kimdk.retrofit_example.post.PostActivity;

import java.util.List;


public class MainActivity extends AppCompatActivity implements MainContract.View {
    private ActivityMainBinding mBinding;
    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setDataBinding();
        initSetting();

    }

    private void setDataBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setActivity(this);
    }

    private void initSetting() {
        presenter = new MainPresenter(this);
        presenter.getMemoList();
        mBinding.memoListRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void updateView(List<Memobean> memoList) {
        MyAdapter adapter = new MyAdapter(memoList, this);
        mBinding.memoListRecycler.setAdapter(adapter);
    }

    @Override
    public void refreshView() {
        presenter.getMemoList();
    }

    @Override
    public void toastMessage(String message) {

    }

    //add_btn 눌렀을때 실행
    public void navigateToPost(View view) {
        Intent intent = new Intent(this, PostActivity.class);
        startActivityForResult(intent, 4000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d("result", "result : " + resultCode + ", resquest : " + requestCode);
        super.onActivityResult(requestCode, resultCode, data);
        presenter.getMemoList();
    }
}


