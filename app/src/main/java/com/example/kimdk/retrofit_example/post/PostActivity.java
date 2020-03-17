package com.example.kimdk.retrofit_example.post;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.kimdk.retrofit_example.R;
import com.example.kimdk.retrofit_example.databinding.ActivityPostBinding;


public class PostActivity extends AppCompatActivity implements PostContract.View {
    private PostContract.Presenter presenter;
    private ActivityPostBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDataBinding();
        initView();
        
    }

    private void setDataBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_post);
        mBinding.setActivity(this);
    }

    private void initView() {
        presenter = new PostPresenter(this);
    }

    public void postButtonClicked() {
        String title =  mBinding.title.getText().toString();
        String content = mBinding.content.getText().toString();

        if(title.isEmpty() || content.isEmpty()) {
            Toast.makeText(this,"한 글자 이상 입력해주세요",Toast.LENGTH_SHORT).show();
        } else {
            presenter.submitPost(title, content);
        }
    }

    @Override
    public void finishPost() {
        finish();
    }
}