package com.example.kimdk.retrofit_example;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);


        final EditText title_et = (EditText) findViewById(R.id.title);
        final EditText content_et = (EditText) findViewById(R.id.content);
        Button modify_button = (Button) findViewById(R.id.confirm);


        Intent intent = new Intent(this.getIntent());

        modify_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nTitle = title_et.getText().toString();
                String nContent = content_et.getText().toString();

                JsonObject object = new JsonObject();
                object.addProperty("title", nTitle);
                object.addProperty("content", nContent);

                RetrofitService service = RetrofitFactory.create();
                service.postMemo(object).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Log.d("success", "suc");
                        }
                        Log.d("success", response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.e("fail", t.toString());
                    }
                });
            }
        });

    }
}