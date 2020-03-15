package com.example.kimdk.retrofit_example.post;

public interface PostContract {
    interface View {
        void finishPost();
    }

    interface Presenter {
        //void setView(View view);
        void submitPost(String title, String content);
    }
}
