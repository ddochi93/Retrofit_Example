package com.example.kimdk.retrofit_example.modify;

import android.util.Log;

import com.example.kimdk.retrofit_example.RetrofitFactory;
import com.example.kimdk.retrofit_example.RetrofitService;
import com.example.kimdk.retrofit_example.data.Memobean;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyPresenter implements ModifyContract.Presenter {
    private ModifyContract.View view;

    public ModifyPresenter( ModifyContract.View view) {
        this.view = view;
    }


    @Override
    public void submitModify(int id, String title, String content) {
        JsonObject object = new JsonObject();
        object.addProperty("id",id);
        object.addProperty("title",title);
        object.addProperty("content",content);

        RetrofitService service = RetrofitFactory.create();
        service.updateMemo(id, object).enqueue(new Callback<Memobean>() {
            @Override
            public void onResponse(Call<Memobean> call, Response<Memobean> response) {
                if(response.isSuccessful()) {
                    Log.d("success", "suc");
                    view.finishModify();
                }
            }

            @Override
            public void onFailure(Call<Memobean> call, Throwable t) {
                Log.e("fail",t.toString());
            }
        });
    }
}
