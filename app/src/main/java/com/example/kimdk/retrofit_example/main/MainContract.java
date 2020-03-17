package com.example.kimdk.retrofit_example.main;

import com.example.kimdk.retrofit_example.data.Memobean;

import java.util.List;

public interface MainContract  {
    interface View {
        void updateView(List<Memobean> memoList);

        void refreshView();

        void toastMessage(String message);
    }

    interface Presenter {
        void getMemoList();
    }
}
