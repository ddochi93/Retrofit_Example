package com.example.kimdk.retrofit_example.post;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kimdk.retrofit_example.R;


public class PostActivity extends AppCompatActivity implements PostContract.View {
    private PostContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        setResult(300); //?

        final EditText title_et = (EditText) findViewById(R.id.title);
        final EditText content_et = (EditText) findViewById(R.id.content);
        Button modify_button = (Button) findViewById(R.id.confirm);
        presenter = new PostPresenter(this);

        modify_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nTitle = title_et.getText().toString();
                String nContent = content_et.getText().toString();

                if(nTitle.isEmpty() || nContent.isEmpty()) {
                    Toast.makeText(view.getContext(),"한 글자 이상 입력해주세요",Toast.LENGTH_SHORT).show();
                    return;
                } else{
                    presenter.submitPost(nTitle, nContent);
                }

            }
        });

    }

    @Override
    public void finishPost() {
        finish();
    }
}