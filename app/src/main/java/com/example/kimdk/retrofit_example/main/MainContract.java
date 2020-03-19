package com.example.kimdk.retrofit_example.main;

import com.example.kimdk.retrofit_example.data.Memobean;

import java.util.List;

public interface MainContract  {
    interface View {
        void updateView(List<Memobean> memoList);

        void removeItemFromAdapter(int position);

        void refreshView();

        void toastMessage(String message);
    }

    interface Presenter {
        void getMemoList();

        void removeFromMemoList(int id, int position);
    }
}
