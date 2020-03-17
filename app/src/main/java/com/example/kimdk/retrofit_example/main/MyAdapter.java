package com.example.kimdk.retrofit_example.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kimdk.retrofit_example.AlertUtil;
import com.example.kimdk.retrofit_example.RetrofitFactory;
import com.example.kimdk.retrofit_example.RetrofitService;
import com.example.kimdk.retrofit_example.data.Memobean;
import com.example.kimdk.retrofit_example.databinding.ItemLab3Binding;
import com.example.kimdk.retrofit_example.modify.ModifyActivity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


class MyAdapter extends RecyclerView.Adapter<MyAdapter.ItemViewHolder> {
    private List<Memobean> memos;
    private FragmentActivity activity;


    public MyAdapter(List<Memobean> memos, FragmentActivity activity) {
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
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemLab3Binding itemLab3Binding = ItemLab3Binding.inflate(inflater, parent, false);
        return new ItemViewHolder(itemLab3Binding, parent.getContext());
        //  ItemLab3Binding itemLab3Binding = DataBindingUtil.inflate()
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, int position) {
        Memobean memo = memos.get(position);
        holder.bind(memo);
        

//        holder.delButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//                builder.setTitle("삭제ㅋ");
//                builder.setMessage("정말 삭제하시겠습니까?");
//                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        int id = Integer.parseInt(holder.itemTimeView.getText().toString());
//                        remove(holder.getAdapterPosition(), id);
//                    }
//                });
//
//                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.cancel();
//                    }
//                });
//                builder.show();
//            }
//
//        });

    }

    @SuppressLint("CheckResult")
    public void remove(int position, int id) {
        RetrofitService retrofitService = RetrofitFactory.create();

        retrofitService.delMemo(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aVoid -> {
                    Log.d("succeeeed", "Good");
                }, throwable -> {
                    Log.e("fail", throwable.toString());
                });

        memos.remove(position);
        notifyItemRemoved(position);

    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private ItemLab3Binding itemLab3Binding;
        private Context context;

        public ItemViewHolder(ItemLab3Binding itemLab3Binding, Context context) {
            super(itemLab3Binding.getRoot());
            this.itemLab3Binding = itemLab3Binding;
            this.context = context;
        }

        public void bind(Memobean memo) {
            itemLab3Binding.setMemo(memo);
            itemLab3Binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertUtil.createDialogWithTitle("수정하시겠습니까?", context, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            int id = memo.getId();
                            String title = memo.getTitle();
                            String content = memo.getContent();

                            Intent intent = new Intent(context, ModifyActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("title", title);
                            intent.putExtra("content", content);

                            //////
                            ((Activity)context).startActivityForResult(intent, 5000);
                        }
                    });
                }
            });
        }
    }

}