package com.example.kimdk.retrofit_example.main;

import com.example.kimdk.retrofit_example.Memobean;

import java.util.List;

public interface MainContract  {
    interface View {
        void updateView(List<Memobean> memoList);

        void refreshView();

        void toastMessage(String message);
    }

    interface Presenter {
        public void setView(View view);

        void getMemoList();
    }
}
