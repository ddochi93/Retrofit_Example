package com.example.kimdk.retrofit_example;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AlertUtil {
    public static void createDialogWithTitle(String title, Context context, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        //builder.setCancelable(true);
        builder.setPositiveButton("예", listener);
        builder.setNegativeButton("아니오", (dialogInterface, i) -> dialogInterface.cancel());
        builder.show();  //builder.create().show();?
    }
}



//    fun createDialogWithTitle(title: String, context:Context, listener: DialogInterface.OnClickListener) {
//        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
//        builder.setTitle(title)
//        builder.setCancelable(true)
//        builder.setPositiveButton("확인", listener)
//        builder.setNegativeButton("취소")  { dialogInterface, _ ->
//                dialogInterface.cancel()
//        }
//        builder.create().show()
//    }


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
//