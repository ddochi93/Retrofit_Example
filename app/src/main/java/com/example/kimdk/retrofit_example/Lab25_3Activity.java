package com.example.kimdk.retrofit_example;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

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


        RetrofitService networkService=RetrofitFactory.create();
        networkService.getList(QUERY, API_KEY, 1, 10)
                .enqueue(new Callback<PageListModel>() {
                    @Override
                    public void onResponse(Call<PageListModel> call, Response<PageListModel> response) {
                        if(response.isSuccessful()){
                            MyAdapter adapter=new MyAdapter(response.body().articles);
                            recyclerView.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<PageListModel> call, Throwable t) {

                    }
                });


    }
    class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView itemTitleView;
        public TextView itemTimeView;
        public TextView itemDescView;
        public ImageView itemImageView;

        public ItemViewHolder(View view) {
            super(view);
            itemTitleView=view.findViewById(R.id.lab3_item_title);
            itemTimeView=view.findViewById(R.id.lab3_item_time);
            itemDescView=view.findViewById(R.id.lab3_item_desc);
            itemImageView=view.findViewById(R.id.lab3_item_image);
        }
    }
    class MyAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        private List<ItemModel> articles;
        public MyAdapter(List<ItemModel> articles) {
            this.articles=articles;
        }
        @Override
        public int getItemCount() {
            return articles.size();
        }

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_lab3, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

            ItemModel item = articles.get(position);

            String author = item.author == null || item.author.isEmpty() ? "Anonymous" : item.author;
            String titleString =  author+" - "+ item.title;

            holder.itemTitleView.setText(titleString);
            holder.itemTimeView.setText(AppUtils.getDate(item.publishedAt)+" at "+ AppUtils.getTime(item.publishedAt));
            holder.itemDescView.setText(item.description);
            Glide.with(Lab25_3Activity.this).load(item.urlToImage).override(250, 200).into(holder.itemImageView);
        }

    }

}


