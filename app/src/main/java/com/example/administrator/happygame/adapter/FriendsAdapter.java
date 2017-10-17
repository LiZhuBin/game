package com.example.administrator.happygame.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.thing_class.Friends;
import com.example.administrator.happygame.util.ApplicationUtil;
import com.example.administrator.happygame.util.GlobalData;
import com.example.administrator.happygame.util.IntentHelp;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 10619 on 2017/9/11.
 */

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder> {
    private Context context;
    private List<Friends> friendsList;

    public FriendsAdapter(List<Friends> friendsList) {
        this.friendsList = friendsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_friends, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.friendView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                context.startActivity(IntentHelp.toPersonActivity(friendsList.get(position).getId(), 0));

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Friends friends = friendsList.get(position);
        Glide.with(ApplicationUtil.getContext()).load(GlobalData.httpAddressPicture + friends.getImageUrl()).into(holder.friendsImage);
        holder.friendsName.setText(friends.getName());
    }

    @Override
    public int getItemCount() {
        return friendsList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView friendsImage;
        View friendView;
        TextView friendsName;

        public ViewHolder(View itemView) {
            super(itemView);
            friendView = itemView;
            friendsImage = (CircleImageView) itemView.findViewById(R.id.friends_image);
            friendsName = (TextView) itemView.findViewById(R.id.friends_name);
        }
    }

}
