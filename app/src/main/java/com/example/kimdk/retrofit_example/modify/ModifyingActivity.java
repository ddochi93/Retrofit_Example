package com.example.kimdk.retrofit_example.modify;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kimdk.retrofit_example.data.Memobean;
import com.example.kimdk.retrofit_example.R;
import com.example.kimdk.retrofit_example.RetrofitFactory;
import com.example.kimdk.retrofit_example.RetrofitService;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ModifyingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);


        final EditText title_et = (EditText)findViewById(R.id.title);
        final EditText content_et = (EditText)findViewById(R.id.content);
        Button modify_button = (Button)findViewById(R.id.modify);


        Intent intent = new Intent(this.getIntent());
        final String title = intent.getStringExtra("title");
        final String content = intent.getStringExtra("content");
        final int id = intent.getIntExtra("id",0);  //0으로 해도 되?

        title_et.setText(title);
        content_et.setText(content);
        modify_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nTitle = title_et.getText().toString();
                String nContent = content_et.getText().toString();

                JsonObject object = new JsonObject();
                object.addProperty("id",id);
                object.addProperty("title",nTitle);
                object.addProperty("content",nContent);

                RetrofitService service = RetrofitFactory.create();
                service.updateMemo(id, object).enqueue(new Callback<Memobean>() {
                    @Override
                    public void onResponse(Call<Memobean> call, Response<Memobean> response) {
                        if(response.isSuccessful()) {
                            Log.d("success","suc");

                            finish();
                        }
                        Log.d("success", response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<Memobean> call, Throwable t) {
                        Log.e("fail",t.toString());
                    }
                });
            }
        });


    }
}
