package com.example.kimdk.retrofit_example.main;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kimdk.retrofit_example.R;
import com.example.kimdk.retrofit_example.RetrofitFactory;
import com.example.kimdk.retrofit_example.RetrofitService;
import com.example.kimdk.retrofit_example.data.Memobean;
import com.example.kimdk.retrofit_example.modify.ModifyActivity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


class MyAdapter extends RecyclerView.Adapter<MyAdapter.ItemViewHolder> {
    private List<Memobean> memos;
    private FragmentActivity activity;

    public MyAdapter(List<Memobean> memos, FragmentActivity activity ) {
        this.memos = memos;
        this.activity = activity;
    }

    @Override
    public int getItemCount() {
        return memos.size();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lab3, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, int position) {

        Memobean memo = memos.get(position);

        Integer id = memo.getId();
        String title = memo.getTitle();
        String content = memo.getContent();

        holder.itemTitleView.setText(title);
        holder.itemTimeView.setText(id.toString());
        holder.itemDescView.setText(content);

        holder.delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("삭제ㅋ");
                builder.setMessage("정말 삭제하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int id = Integer.parseInt(holder.itemTimeView.getText().toString());
                        remove(holder.getAdapterPosition(), id);
                    }
                });

                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
            }

        });


        ///
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(holder.itemDescView.getContext(), holder.itemTitleView.getText().toString(), Toast.LENGTH_SHORT).show();
                final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("수정");
                builder.setMessage("수정하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int id = Integer.parseInt(holder.itemTimeView.getText().toString());
                        String title = holder.itemTitleView.getText().toString();
                        String content = holder.itemDescView.getText().toString();

                        Intent intent = new Intent(activity, ModifyActivity.class);
                        intent.putExtra("id", id);
                        intent.putExtra("title", title);
                        intent.putExtra("content", content);

                        //////
                        activity.startActivityForResult(intent,5000); //delButton으로 가능?

                    }
                });


                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
            }
        });

    }

    @SuppressLint("CheckResult")
    public void remove(int position, int id) {
        RetrofitService retrofitService = RetrofitFactory.create();

        retrofitService.delMemo(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( aVoid -> {
                    Log.d("succeeeed", "Good");
                },throwable -> {
                    Log.e("fail",throwable.toString());
                });

        memos.remove(position);
        notifyItemRemoved(position);

    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView itemTitleView;
        public TextView itemTimeView;
        public TextView itemDescView;
        public Button delButton;

        public ItemViewHolder(View view) {
            super(view);
            itemTitleView = view.findViewById(R.id.lab3_item_title);
            itemTimeView = view.findViewById(R.id.lab3_item_time);
            itemDescView = view.findViewById(R.id.lab3_item_desc);
            delButton = view.findViewById(R.id.del_button);
        }
    }

}