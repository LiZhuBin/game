package com.example.administrator.happygame.adapter;

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
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.activity.ForumActivity;
import com.example.administrator.happygame.thing_class.ForumItem;
import com.example.administrator.happygame.util.GlobalData;
import com.example.administrator.happygame.util.MyApplication;

import java.util.List;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

public class ForumAdapter extends RecyclerView.Adapter<ForumAdapter.ViewHolder> {

    private static final String TAG = "ForumAdapter";
    private Context mContext;
    private List<ForumItem> mforumItem;

    public ForumAdapter(List<ForumItem> mforumItem) {
        this.mforumItem = mforumItem;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();

        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.forum_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(MyApplication.getContext(), ForumActivity.class);
                intent.putExtra("id", mforumItem.get(position).getId());
                mContext.startActivity(intent);
            }
        });



        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ForumItem forumItem = mforumItem.get(position);
        holder.forumTitle.setText(forumItem.getForumTitle());
        Glide.with(MyApplication.getContext()).load(GlobalData.HTTP_ADDRESS_PICTURE + forumItem.getForumImage()).into(holder.forumImage);

    }

    @Override
    public int getItemCount() {
        return mforumItem.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView forumTitle;
        ImageView forumImage;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            forumTitle = (TextView) view.findViewById(R.id.forum_title);
            forumImage = (ImageView) view.findViewById(R.id.forum_image);
        }
    }


}

