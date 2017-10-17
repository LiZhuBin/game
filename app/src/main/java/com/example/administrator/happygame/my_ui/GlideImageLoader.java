package com.example.administrator.happygame.my_ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.happygame.activity.NewsActivity;
import com.example.administrator.happygame.been.News;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by Administrator on 2017/9/27 0027.
 */

public class GlideImageLoader extends ImageLoader {

    private Banner banner; // 传入banner对象是为了在这里面顺便完成设标题的任务！


    // 这里是为了处理，对加载进来的 List

    @Override
    public void displayImage(final Context context, Object path, ImageView imageView) {      //对传入的list的每一项进行处理

        //传进来的path 可能是 Url 或 本地路径int

        if (path instanceof Integer) {
            //path为本地路径较为 R.ID......

            Glide.with(context).load((int) path).into(imageView); //载入到imageview中

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        } else if (path instanceof News)  //如果传入的是新闻，那么直接提取里面的新闻让后去展示图片
        {
            final News one = (News) path;
            int Id = one.getNew_drawable();
            String title = one.getNew_title(); //这个title可能要通过其他地方去实现添加

            Glide.with(context).load(Id).into(imageView);

            //设点击事件！来跳转到News地方去

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NewsActivity.class);
                    intent.putExtra("Object_news", one);
                    context.startActivity(intent);
                }
            });


        }


    }


}

