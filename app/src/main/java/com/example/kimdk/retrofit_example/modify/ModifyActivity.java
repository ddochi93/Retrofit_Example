package com.example.kimdk.retrofit_example.modify;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.kimdk.retrofit_example.R;
import com.example.kimdk.retrofit_example.databinding.ActivityModifyBinding;


public class ModifyActivity extends AppCompatActivity implements ModifyContract.View {
    private ActivityModifyBinding mBinding;
    private int id;
    private ModifyContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDataBinding();
        initView();
    }

    private void setDataBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_modify);
        mBinding.setActivity(this);
    }

    @Override
    public void initView() {
        presenter = new ModifyPresenter(this);

        Intent intent = new Intent(this.getIntent());
        final String title = intent.getStringExtra("title");
        final String content = intent.getStringExtra("content");
        id = intent.getIntExtra("id", -1);
        mBinding.title.setText(title);
        mBinding.content.setText(content);


    }


    //modify_btn 눌렸을때 실행.
    public void modifyButtonClicked(){
        String nTitle = mBinding.title.getText().toString();
        String nContent = mBinding.content.getText().toString();

        if(nTitle.isEmpty() || nContent.isEmpty()) {
            Toast.makeText(this,"한 글자 이상 입력해주세요",Toast.LENGTH_SHORT).show();
        } else{
            presenter.submitModify(id, nTitle, nContent);
        }
    }

    @Override
    public void finishModify() {
        finish();
    }
}
