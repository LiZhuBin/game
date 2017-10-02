package com.example.administrator.myapplication.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.been.Activity;
import com.example.administrator.myapplication.util.ApplicationUtil;
import com.example.administrator.myapplication.util.GlobalData;
import com.example.administrator.myapplication.util.HttpUtil;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddActivity extends BaseActivity {
    Activity thisActivity;
    ImageView activityAddImage;
    String obj;
    private static final String TAG = "AddActivity";
    @Bind(R.id.add_background)
    CoordinatorLayout addBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        activityAddImage = (ImageView) findViewById(R.id.image_inside);
        Toolbar toolbar = (Toolbar) findViewById(R.id.newactivity_toolbar);
        setSupportActionBar(toolbar);
        getData();
        ImageView imageView = (ImageView) findViewById(R.id.Back_button);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public void getData() {
        Intent intent = getIntent();
        String obj = (String) intent.getSerializableExtra("Object_userId");

        RequestBody body = new FormBody.Builder()
                .add("id", obj)//添加键值对
                .build();
        HttpUtil.sendOkHttpResquest(GlobalData.httpAddressActivity + "php/getById.php", body, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                thisActivity = HttpUtil.getSingleActivity(response);
                new MyAsyncTask(thisActivity).execute();
            }
        });
    }

    class MyAsyncTask extends AsyncTask<Void, Integer, Boolean> {
        Activity activity;

        public MyAsyncTask(Activity activity) {
            super();
            this.activity = activity;
        }

        @Override
        protected void onPreExecute() {


        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {


        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            String url = GlobalData.httpAddressPicture + thisActivity.getImage();
              Glide.with(ApplicationUtil.getContext()).load(url).into(activityAddImage);

        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            publishProgress(1);
            return true;
        }
    }

}
