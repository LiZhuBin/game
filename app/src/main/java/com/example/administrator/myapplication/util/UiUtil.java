package com.example.administrator.myapplication.util;

import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.sackcentury.shinebuttonlib.ShineButton;
import com.wx.goodview.GoodView;

/**
 * Created by Administrator on 2017/9/10 0010.
 */

public class UiUtil {
   public static GoodView mGoodView= new GoodView(ApplicationUtil.getContext());

    public static void photoView(final ImageView imageView,final ImageView outImageView, final FrameLayout frameLayout, final PhotoView photoView){
 photoView.enable();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UiUtil.userPhotoView(imageView,frameLayout,photoView);
            }
        });
        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtil.closePhotoView(imageView,outImageView,frameLayout,photoView);
            }
        });
    }
  public static void userPhotoView(ImageView imageView, FrameLayout frameLayout, PhotoView photoView){
      Info  mInfo = PhotoView.getImageViewInfo(imageView);
      imageView.setVisibility(View.GONE);
      frameLayout.setVisibility(View.VISIBLE);
      photoView.setVisibility(View.VISIBLE);
      photoView.setImageDrawable(imageView.getDrawable());
      photoView.animaFrom(mInfo);
  }
  public static void closePhotoView(final ImageView imageView,final ImageView outImageView, final FrameLayout frameLayout, final PhotoView photoView){
      Info  mInfo = PhotoView.getImageViewInfo(outImageView);
      photoView.animaTo(mInfo, new Runnable() {
          @Override
          public void run() {
              photoView.setVisibility(View.GONE);
              frameLayout.setVisibility(View.GONE);
              imageView.setVisibility(View.VISIBLE);
          }
      });
  }
    public static void good(final ShineButton shineButton, final String str, final TextView textView){

        shineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MyAsyncTask(shineButton ,str,textView,view).execute();

            }
        });
    }
    static class MyAsyncTask extends AsyncTask<Void,Integer,Boolean> {
        TextView textView;
        ShineButton shineButton;
        String str;
        View view;
        public MyAsyncTask(ShineButton shineButton,String str,TextView textView,View view) {
            super();
            this.textView=textView;
            this.shineButton=shineButton;
            this.str=str;
            this.view=view;
        }

        @Override
        protected void onPreExecute() {



        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int textNum=Integer.parseInt(textView.getText().toString());
            if(shineButton.isChecked()) {
                if (textView!=null){
                textView.setText(textNum+1+"");
            }
                mGoodView.setTextInfo(str, Color.RED,20);
                mGoodView.show(view);


            }else {
                if(textView!=null){
                    textView.setText(textNum-1+"");
                }
            }

        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            publishProgress(1);
            return true;
        }
    }
}
