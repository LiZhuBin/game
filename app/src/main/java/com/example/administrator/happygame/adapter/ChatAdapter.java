package com.example.administrator.happygame.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.thing_class.ChatItem;
import com.example.administrator.happygame.util.GlobalData;
import com.example.administrator.happygame.util.IntentHelp;

import java.util.List;

/**
 * 作者：Administrator on 2017/11/1 0001 12:02
 * 邮箱：xjs250@163.com
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.HeadViewHolder> {

    private List<ChatItem> list;
    private Context context;


    public ChatAdapter(List<ChatItem> list) {
        this.list = list;
    }

    @Override
    public HeadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (context == null) {
            context = parent.getContext();
        }

        View view = LayoutInflater.from(context).inflate(R.layout.item_chat, parent, false);

        final HeadViewHolder holder = new HeadViewHolder(view);
        holder.view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                context.startActivity(IntentHelp.toChatActivity(list.get(position).getBuildId()));
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(HeadViewHolder holder, int position) {

        Glide.with(context).load(GlobalData.HTTP_ADDRESS_PICTURE + list.get(position).getImageUrl()).into(holder.imageview);


        holder.chatName.setText(list.get(position).getChatName());
        holder.chatMessage.setText(list.get(position).getChatContent());
        holder.pos = position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HeadViewHolder extends RecyclerView.ViewHolder {
        int pos = 0;
        View view;
        ImageView imageview;
        TextView chatName;
        TextView chatMessage;

        public HeadViewHolder(View itemView) {

            super(itemView);
            view = itemView;

            imageview = (ImageView) itemView.findViewById(R.id.chat_image);
            chatName = (TextView) itemView.findViewById(R.id.chat_name);
chatMessage=(TextView)itemView.findViewById(R.id.chat_message);
        }
    }
}
