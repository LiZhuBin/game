package com.example.administrator.happygame.util;

import android.content.Intent;
import android.widget.ImageView;

import com.example.administrator.happygame.LoginActivity;
import com.example.administrator.happygame.activity.ChatActivity;
import com.example.administrator.happygame.activity.FriendsActivity;
import com.example.administrator.happygame.activity.MyInformationActivity;
import com.example.administrator.happygame.activity.PersonActivity;
import com.example.administrator.happygame.activity.SearchActivity;
import com.example.administrator.happygame.activity.SetActivity;
import com.example.administrator.happygame.activity.fifth.CollectActivity;
import com.example.administrator.happygame.activity.fifth.MapActivity;
import com.example.administrator.happygame.activity.fifth.ZxingActivity;
import com.example.administrator.happygame.been.User;

/**
 * Created by Administrator on 2017/10/3 0003.
 */

public class IntentHelp {

    public static Intent toFriendsActivity(String friends, String title) {
        Intent intent = new Intent(MyApplication.getContext(), FriendsActivity.class);
        intent.putExtra("friends", friends);
        intent.putExtra("title", title);
        return intent;
    }

    public static Intent toPersonActivity(String userId, int pager) {
        Intent intent = new Intent(MyApplication.getContext(), PersonActivity.class);
        intent.putExtra("id", userId);
        intent.putExtra("pager", pager);
        return intent;
    }

    public static Intent toSetActivity() {
        Intent intent = new Intent(MyApplication.getContext(), SetActivity.class);
        return intent;
    }

    public static Intent toLoginActivity(ImageView imageView) {
        Intent intent = new Intent(MyApplication.getContext(), LoginActivity.class);
        imageView.setDrawingCacheEnabled(Boolean.TRUE);
        intent.putExtra("BITMAP", imageView.getDrawingCache());
        return intent;
    }

    public static Intent toMyInformationActivity(User user, ImageView imageView) {
        Intent intent = new Intent(MyApplication.getContext(), MyInformationActivity.class);
        imageView.setDrawingCacheEnabled(Boolean.TRUE);
        intent.putExtra("BITMAP", imageView.getDrawingCache());
        intent.putExtra("USER", user);
        return intent;
    }
    public static Intent toCollectActivity(String userId) {
        Intent intent = new Intent(MyApplication.getContext(), CollectActivity.class);
        intent.putExtra("id", userId);
        return intent;
    }

public static Intent toMapActivity(String poi){
        Intent intent=new Intent(MyApplication.getContext(), MapActivity.class);
        intent.putExtra("Address", poi);
        return intent;
    }
    public static Intent toChatActivity(String id){
        Intent intent=new Intent(MyApplication.getContext(), ChatActivity.class);
        intent.putExtra("id", id);
        return intent;
    }
    public static Intent toZxingActivity(ImageView imageView) {
        Intent intent = new Intent(MyApplication.getContext(), ZxingActivity.class);
        imageView.setDrawingCacheEnabled(Boolean.TRUE);
        intent.putExtra("BITMAP", imageView.getDrawingCache());
        return intent;
    }
    public static Intent toSearchActivity(String data){
        Intent intent=new Intent(MyApplication.getContext(), SearchActivity.class);
        intent.putExtra("DATA", data);
        return intent;
    }
}
