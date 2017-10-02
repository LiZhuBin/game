package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.activity.NewsActivity;
import com.example.administrator.myapplication.been.News;
import com.example.administrator.myapplication.util.GlobalData;
import com.example.administrator.myapplication.util.LogUtil;

import java.util.List;

/**
 * Created by lenovo on 2017/9/6.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private List<News> news_list ;
    private Context myContext;

    public MyRecyclerAdapter(List<News> list )
    {
        news_list = list ;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (myContext == null){ myContext = parent.getContext() ;} //我认为这里的parent 是整个recycler的view，所以从这里获取上下文

        View view = LayoutInflater.from(myContext).inflate(R.layout.cardview,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        News Currentview = news_list.get(position);
        holder.ima_title.setText(Currentview.getNew_title());
        holder.bottom_title.setText(Currentview.getNew_content());
        LogUtil.d(Currentview.getNew_image()+"-----------------------");
        Glide.with(myContext).load(GlobalData.httpAddressPicture+Currentview.getNew_image()).into(holder.ima_big);
        holder.pos=position;
    }

    @Override
    public int getItemCount() {
        return news_list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
            //ViewHolder需要获取一个view，然后获取旗下的控件
        int       pos = 0 ;
        ImageView ima_big ;
        TextView ima_title;
        TextView bottom_title;
        public ViewHolder(View itemView) {
            super(itemView);
            ima_big = (ImageView)itemView.findViewById(R.id.Big_Image);
            ima_title = (TextView)itemView.findViewById(R.id.Photo_title);
            bottom_title = (TextView)itemView.findViewById(R.id.BottomText);

                    itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(myContext, NewsActivity.class);
                   intent.putExtra("Object_news",news_list.get(pos));
                    myContext.startActivity(intent);


                }
            });

        }
    }


}
