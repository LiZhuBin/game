package com.example.administrator.happygame.util;

import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.example.administrator.happygame.been.User;
import com.example.administrator.happygame.main_fragment.UserFragment;
import com.jaouan.revealator.Revealator;
import com.melnykov.fab.FloatingActionButton;
import com.sackcentury.shinebuttonlib.ShineButton;
import com.wx.goodview.GoodView;

import net.frakbot.jumpingbeans.JumpingBeans;

import static com.example.administrator.happygame.util.GlobalData.mUserDao;

/**
 * Created by Administrator on 2017/9/10 0010.
 */

public class UiUtil {
    public static GoodView mGoodView = new GoodView(MyApplication.getContext());

    public static void photoView(final ImageView imageView, final ImageView outImageView, final FrameLayout frameLayout, final PhotoView photoView) {
        photoView.enable();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UiUtil.userPhotoView(imageView, frameLayout, photoView);
            }
        });
        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtil.closePhotoView(imageView, outImageView, frameLayout, photoView);
            }
        });
    }

    public static void userPhotoView(ImageView imageView, FrameLayout frameLayout, PhotoView photoView) {
        Info mInfo = PhotoView.getImageViewInfo(imageView);
        imageView.setVisibility(View.GONE);
        frameLayout.setVisibility(View.VISIBLE);
        photoView.setVisibility(View.VISIBLE);
        photoView.setImageDrawable(imageView.getDrawable());
        photoView.animaFrom(mInfo);
    }

    public static void closePhotoView(final ImageView imageView, final ImageView outImageView, final FrameLayout frameLayout, final PhotoView photoView) {
        Info mInfo = PhotoView.getImageViewInfo(outImageView);
        photoView.animaTo(mInfo, new Runnable() {
            @Override
            public void run() {
                photoView.setVisibility(View.GONE);
                frameLayout.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
            }
        });
    }

    public static void jumpBean(TextView textView) {
        JumpingBeans jumpingBeans1 = JumpingBeans.with(textView)
                .appendJumpingDots()
                .build();
    }

    public static void revealatFab(final FrameLayout theAwesomeView, final FloatingActionButton opeanButton, final Button closeButton) {

    reveal(theAwesomeView,opeanButton);
        unreveal(theAwesomeView,opeanButton,closeButton);
    }
    public static void revealatFab(final FrameLayout theAwesomeView, final FloatingActionButton opeanButton, final Button closeButton1,final  Button closeButton2) {

        reveal(theAwesomeView,opeanButton);
        unreveal(theAwesomeView,opeanButton,closeButton1);
        unreveal(theAwesomeView,opeanButton,closeButton2);
    }
public static void reveal(final FrameLayout theAwesomeView, final FloatingActionButton opeanButton){
    opeanButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Revealator.reveal(theAwesomeView)
                    .from(opeanButton)
                    .withCurvedTranslation()
                    .withChildsAnimation()
                    .start();

        }
    });
}
public static void unreveal(final FrameLayout theAwesomeView,final FloatingActionButton opeanButton,final Button closeButton){
    closeButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Revealator.unreveal(theAwesomeView)
                    .to(opeanButton)
                    .withCurvedTranslation()
                    .start();
        }
    });
}


    public static void good(final ShineButton shineButton, final String str, final TextView textView, final User user) {

        shineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MyAsyncTask(shineButton, str, textView, view, user).execute();

            }
        });
    }

    static class MyAsyncTask extends AsyncTask<Void, Integer, Boolean> {
        TextView textView;
        ShineButton shineButton;
        String str;
        View view;
User user;
        public MyAsyncTask(ShineButton shineButton, String str, TextView textView, View view,User user) {
            super();
            this.textView = textView;
            this.shineButton = shineButton;
            this.str = str;
            this.view = view;
            this.user=user;
        }

        @Override
        protected void onPreExecute() {


        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int textNum = Integer.parseInt(textView.getText().toString());
            if (shineButton.isChecked()) {
                if (textView != null) {
                    textView.setText(textNum + 1 + "");
                    user.setPraise_id(user.getPraise_id()+"|"+ UserFragment.me.getId());

                }
                mGoodView.setTextInfo(str, Color.RED, 20);
                mGoodView.show(view);


            } else {
                if (textView != null) {
                    textView.setText(textNum - 1 + "");
                    int lastIndex=user.getPraise_id().lastIndexOf("|");
                    user.setPraise_id(user.getPraise_id().substring(0,lastIndex+1));
                }
            }
            mUserDao.update(user);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            publishProgress(1);
            return true;
        }
    }
}
