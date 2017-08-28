package com.example.administrator.myapplication.util;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by Administrator on 2017/8/22 0022.
 */

public class DialogUtil {
    public static void showDialog(Context context,String message){
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("确定",null)
                .create()
                .show();
    }
}
