package com.example.administrator.happygame.util;

import android.content.Intent;
import android.widget.ImageView;

import com.example.administrator.happygame.LoginActivity;
import com.example.administrator.happygame.activity.FriendsActivity;
import com.example.administrator.happygame.activity.MyInformationActivity;
import com.example.administrator.happygame.activity.PersonActivity;
import com.example.administrator.happygame.activity.SetActivity;
import com.example.administrator.happygame.been.User;

/**
 * Created by Administrator on 2017/10/3 0003.
 */

public class IntentHelp {

    public static Intent toFriendsActivity(String friends, String title) {
        Intent intent = new Intent(com.example.administrator.happygame.util.ApplicationUtil.getContext(), FriendsActivity.class);
        intent.putExtra("friends", friends);
        intent.putExtra("title", title);
        return intent;
    }

    public static Intent toPersonActivity(String userId, int pager) {
        Intent intent = new Intent(com.example.administrator.happygame.util.ApplicationUtil.getContext(), PersonActivity.class);
        intent.putExtra("id", userId);
        intent.putExtra("pager", pager);
        return intent;
    }

    public static Intent toSetActivity() {
        Intent intent = new Intent(com.example.administrator.happygame.util.ApplicationUtil.getContext(), SetActivity.class);
        return intent;
    }
    public static Intent toLoginActivity(ImageView imageView) {
        Intent intent=new Intent(ApplicationUtil.getContext(), LoginActivity.class);
        imageView.setDrawingCacheEnabled(Boolean.TRUE);
        intent.putExtra("BITMAP", imageView.getDrawingCache());
        return intent;
    }
    public static Intent toMyInformationActivity(User user,ImageView imageView)
    {
        Intent intent=new Intent(ApplicationUtil.getContext(), MyInformationActivity.class);
        imageView.setDrawingCacheEnabled(Boolean.TRUE);
        intent.putExtra("BITMAP", imageView.getDrawingCache());
        intent.putExtra("USER",user);
        return intent;
    }


}
