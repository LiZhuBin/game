package com.example.administrator.myapplication.util;

import android.content.Intent;

import com.example.administrator.myapplication.activity.FriendsActivity;
import com.example.administrator.myapplication.activity.PersonActivity;

/**
 * Created by Administrator on 2017/10/3 0003.
 */

public class IntentHelp {
    public static Intent toFriendsActivity(String friends,String title){
        Intent intent=new Intent(ApplicationUtil.getContext(),FriendsActivity.class);
        intent.putExtra("friends",friends);
        intent.putExtra("title",title);
        return intent;
    }
    public static Intent toPersonActivity(String userId){
        Intent intent = new Intent(ApplicationUtil.getContext(), PersonActivity.class);
        intent.putExtra("id",userId);
        return intent;
    }
}
