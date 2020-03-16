package com.example.kimdk.retrofit_example.modify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kimdk.retrofit_example.R;


public class ModifyActivity extends AppCompatActivity implements ModifyContract.View {

    private EditText title_et;
    private EditText content_et;
    private Button modify_button;
    private int id;
    private ModifyContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        presenter = new ModifyPresenter(this);

        title_et = findViewById(R.id.title);
        content_et = findViewById(R.id.content);
        modify_button = findViewById(R.id.modify);
        initView();
    }

    @Override
    public void initView() {
        title_et = findViewById(R.id.title);
        content_et = findViewById(R.id.content);
        modify_button = findViewById(R.id.modify);

        Intent intent = new Intent(this.getIntent());
        final String title = intent.getStringExtra("title");
        final String content = intent.getStringExtra("content");
        id = intent.getIntExtra("id", -1);

        title_et.setText(title);
        content_et.setText(content);


        modify_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nTitle = title_et.getText().toString();
                String nContent = content_et.getText().toString();

                if(nTitle.isEmpty() || nContent.isEmpty()) {
                    Toast.makeText(view.getContext(),"한 글자 이상 입력해주세요",Toast.LENGTH_SHORT).show();
                } else{
                    presenter.submitModify(id, nTitle, nContent);
                }
            }
        });

    }

    @Override
    public void finishModify() {
        finish();
    }
}
