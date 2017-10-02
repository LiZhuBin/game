package com.example.administrator.myapplication.activity;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.util.ApplicationUtil;

public class PhotoViewActivity extends AppCompatActivity {
    PhotoView userMySmallImagePhoto;
    private static final String TAG = "PhotoViewActivity";
   Drawable drawable;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        userMySmallImagePhoto = (PhotoView) findViewById(R.id.photoview);
        userMySmallImagePhoto.enable();
        // userMySmallImagePhoto.setImageDrawable();
        String url = getIntent().getExtras().getString("imageUrl");

        new MyAsyncTask(url).execute();
        //userMySmallImagePhoto.setImageBitmap((Bitmap) getIntent().getExtras().getParcelable("bitmap"));

        userMySmallImagePhoto.setImageDrawable(drawable);
        userMySmallImagePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
        class MyAsyncTask extends AsyncTask<Void,Integer,Boolean> {
            String url;
            public MyAsyncTask(String url) {
                super();
                this.url=url;
            }

            @Override
            protected void onPreExecute() {



            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {



            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                Glide.with(ApplicationUtil.getContext()).load(url).crossFade()
                        .into(userMySmallImagePhoto);
            }

            @Override
            protected Boolean doInBackground(Void... voids) {
                publishProgress(1);
                return true;
            }
        }
}
