package com.example.kimdk.retrofit_example.main.adapter;

public interface MainAdapterContract {
    interface View {
        void notifyAdapter(int position);
    }

    interface Model {
        void removeItem(int position);
    }
}