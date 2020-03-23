package com.example.kimdk.retrofit_example.main.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kimdk.retrofit_example.AlertUtil;
import com.example.kimdk.retrofit_example.data.Memobean;
import com.example.kimdk.retrofit_example.databinding.ItemLab3Binding;
import com.example.kimdk.retrofit_example.main.MainContract;
import com.example.kimdk.retrofit_example.modify.ModifyActivity;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ItemViewHolder> implements MainAdapterContract.View, MainAdapterContract.Model {


    private List<Memobean> memos;
    private MainContract.Presenter presenter;
    //private FragmentActivity activity;


    public MyAdapter(List<Memobean> memos, MainContract.Presenter presenter) {
        this.memos = memos;
        this.presenter = presenter;
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
        // ItemLab3Binding itemLab3Binding = DataBindingUtil.inflate()
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, int position) {
        Memobean memo = memos.get(position);
        holder.bind(memo);

        Log.d("possition", position + " , " + holder.getAdapterPosition());


    }

//    public List<Memobean> getMemos() {
//        return memos;
//    }

    @Override
    public void notifyAdapter(int position) {
       // notifyItemChanged(position);
        this.notifyItemRemoved(position);
        //notifyItemRemoved(position);
    }

    @Override
    public void removeItem(int position) {
        memos.remove(position);
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
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
                            ((Activity) context).startActivityForResult(intent, 5000);
                        }
                    });
                }
            });

            itemLab3Binding.delButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertUtil.createDialogWithTitle("삭제 하시겠어요?", context, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            presenter.removeFromMemoList(memo.getId(), getAdapterPosition());
                            Log.d("possition", " , 2:" + getAdapterPosition());
                        }
                    });
                }
            });

        }
    }
}