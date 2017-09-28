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
import com.example.administrator.myapplication.activity.PersonActivity;
import com.example.administrator.myapplication.util.ApplicationUtil;
import com.example.administrator.myapplication.zairuclas.Headview;

import java.util.List;


/**
 * Created by lenovo on 2017/9/14.
 */

public class headviewAdapter extends RecyclerView.Adapter<headviewAdapter.HeadViewHolder> {

    private List<Headview> list ;
    private Context context;

    public headviewAdapter(List<Headview> list) {
        this.list = list ;
    }

    @Override
    public HeadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (context == null )
       {
           context = parent.getContext();
       }

       View view = LayoutInflater.from(context).inflate(R.layout.circle_people_headview,parent,false);
       final HeadViewHolder holder=new HeadViewHolder(view);
        holder.view.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                int position=holder.getAdapterPosition();
                Intent intent=new Intent(ApplicationUtil.getContext(), PersonActivity.class);
                intent.putExtra("Object_news",list.get(position));
               context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(HeadViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getImageview_url()).into(holder.imageview);
        holder.userName.setText(list.get(position).getImageview_name());
        holder.pos=position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HeadViewHolder extends RecyclerView.ViewHolder {
        int       pos = 0 ;
        View view;
        ImageView imageview ;
        TextView userName;
        public HeadViewHolder(View itemView) {

            super(itemView);
            view = itemView;

            imageview = (ImageView) itemView.findViewById(R.id.Circleimageview);
            userName = (TextView) itemView.findViewById(R.id.circle_people_name);

        }}}
