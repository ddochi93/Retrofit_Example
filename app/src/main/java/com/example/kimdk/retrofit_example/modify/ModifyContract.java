package com.example.kimdk.retrofit_example.modify;

public interface ModifyContract {
    interface View {
        void initView();
        void finishModify();
    }

    interface Presenter {
        void submitModify(int id, String title, String content);
    }
}
