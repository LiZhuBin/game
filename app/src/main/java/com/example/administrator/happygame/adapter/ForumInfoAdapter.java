package com.example.administrator.happygame.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.my_ui.ActivityForumItem;
import com.example.administrator.happygame.util.GlobalData;
import com.example.administrator.happygame.util.MyApplication;
import com.example.administrator.happygame.util.TimeUtil;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Administrator on 2017/9/15 0015.
 */

public class ForumInfoAdapter extends RecyclerView.Adapter<ForumInfoAdapter.ViewHolder> {
    private Context mContext;
    private List<ActivityForumItem> mforumItem;

    public ForumInfoAdapter(List<ActivityForumItem> mforumItem) {
        this.mforumItem = mforumItem;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_forum_comment, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ActivityForumItem forumItem = mforumItem.get(position);
        Glide.with(MyApplication.getContext()).load(GlobalData.HTTP_ADDRESS_PICTURE + forumItem.getmImageUrl()).into(holder.mIcon_image);
        holder.mUsername.setText(forumItem.getmUsername());
        holder.mLou.setText(forumItem.getmLou());
        try {
            holder.mTime.setText(TimeUtil.getTimeFormatText(forumItem.getmTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.mDetile.setText(forumItem.getmDetile());
    }

    @Override
    public int getItemCount() {
        return mforumItem.size();
    }

    // Content View Elements


    // End Of Content View Elements
    static class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout mRelativeLayout;
        public de.hdodenhof.circleimageview.CircleImageView mIcon_image;
        public TextView mUsername;
        public TextView mLou;
        public TextView mTime;
        public TextView mDetile;


        public ViewHolder(View view) {
            super(view);
            mRelativeLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout);
            mIcon_image = (de.hdodenhof.circleimageview.CircleImageView) view.findViewById(R.id.icon_image);
            mUsername = (TextView) view.findViewById(R.id.username);
            mLou = (TextView) view.findViewById(R.id.lou);
            mTime = (TextView) view.findViewById(R.id.time);
            mDetile = (TextView) view.findViewById(R.id.detile);

        }
    }


}
