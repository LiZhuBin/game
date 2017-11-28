package com.example.administrator.happygame.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.thing_class.Friends;
import com.example.administrator.happygame.util.GlobalData;
import com.example.administrator.happygame.util.MyApplication;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：Administrator on 2017/11/28 0028 17:09
 * 邮箱：xjs250@163.com
 */

public class InviteAdapter extends RecyclerView.Adapter<InviteAdapter.ViewHolder>{
    private Context context;
    private List<Friends> friendsList;
    private SparseBooleanArray selectLists = new SparseBooleanArray();
    public InviteAdapter(List<Friends> friendsList) {
        this.friendsList = friendsList;
    }

    @Override
    public InviteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_friends, parent, false);
        final InviteAdapter.ViewHolder holder = new InviteAdapter.ViewHolder(view);
        holder.friendView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (holder.selectedMarkImg.getVisibility() == View.GONE) {
                    holder.selectedMarkImg.setVisibility(View.VISIBLE);
                    selectLists.put(holder.getAdapterPosition(), true);
                } else if (holder.selectedMarkImg.getVisibility() == View.VISIBLE){
                    holder.selectedMarkImg.setVisibility(View.GONE);
                    selectLists.put(holder.getAdapterPosition(), false);
                }

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(InviteAdapter.ViewHolder holder, int position) {
        Friends friends = friendsList.get(position);
        Glide.with(MyApplication.getContext()).load(GlobalData.HTTP_ADDRESS_PICTURE + friends.getImageUrl()).into(holder.friendsImage);
        holder.friendsName.setText(friends.getName());
        if (!selectLists.get(position)) {
            holder.selectedMarkImg.setVisibility(View.GONE);
        } else {
            holder.selectedMarkImg.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return friendsList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView friendsImage;
        View friendView;
        TextView friendsName;
ImageView selectedMarkImg;
        public ViewHolder(View itemView) {
            super(itemView);
            friendView = itemView;
            friendsImage = (CircleImageView) itemView.findViewById(R.id.friends_image);
            friendsName = (TextView) itemView.findViewById(R.id.friends_name);
            selectedMarkImg=(ImageView)itemView.findViewById(R.id.selectedMarkImg);
        }
    }}
