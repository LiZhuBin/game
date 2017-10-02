package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.activity.ForumActivity;
import com.example.administrator.myapplication.thing_class.ForumItem;
import com.example.administrator.myapplication.util.ApplicationUtil;
import com.example.administrator.myapplication.util.GlobalData;
import com.example.administrator.myapplication.util.StringUtil;
import com.sackcentury.shinebuttonlib.ShineButton;

import java.util.List;

import static com.example.administrator.myapplication.util.UiUtil.good;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

public class ForumAdapter extends RecyclerView.Adapter<ForumAdapter.ViewHolder> {

    private Context mContext;
    private List<ForumItem> mforumItem;

    private static final String TAG = "ForumAdapter";
    public ForumAdapter(List<ForumItem> mforumItem) {
        this.mforumItem = mforumItem;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext==null){
            mContext=parent.getContext();

        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.forum_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position =holder.getAdapterPosition();
                Intent intent=new Intent(ApplicationUtil.getContext(), ForumActivity.class);
                intent.putExtra("Object_forum",mforumItem.get(position));
                mContext.startActivity(intent);
            }
        });


        final ShineButton shineButton1 = (ShineButton) view.findViewById(R.id.forum_shineButton_like);
        final ShineButton shineButton2 = (ShineButton) view.findViewById(R.id.forum_shineButton_heart);
        good(view,shineButton1,"+1");
        good(view,shineButton2,"收藏成功");
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            ForumItem forumItem=mforumItem.get(position);
            holder.forumTitle.setText(forumItem.getForumTitle());
        Glide.with(ApplicationUtil.getContext()).load(GlobalData.httpAddressPicture+forumItem.getForumImage()).into(holder.forumImage);
        holder.forumAddNum.setText(forumItem.getForumAddNum());
        holder.forumLikeNum.setText(forumItem.getForumLikeNum());
        holder.forumAddNum.setText(StringUtil.httpArrayStringLength(forumItem.getForumAddNum()));
    }

    @Override
    public int getItemCount() {
        return mforumItem.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView forumTitle,forumAddNum,forumLikeNum;
        ImageView forumImage;
        public ViewHolder(View view){
            super(view);
            cardView=(CardView)view;
            forumTitle=(TextView)view.findViewById(R.id.forum_title);
            forumImage=(ImageView) view.findViewById(R.id.forum_image);
            forumAddNum=(TextView)view.findViewById(R.id.forum_add_num);
            forumLikeNum=(TextView)view.findViewById(R.id.forum_like_num);
        }
    }




}

