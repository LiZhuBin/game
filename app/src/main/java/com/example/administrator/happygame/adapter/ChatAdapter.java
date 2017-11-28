package com.example.administrator.happygame.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.been.Chat;
import com.example.administrator.happygame.util.GlobalData;
import com.example.administrator.happygame.util.IntentHelp;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：Administrator on 2017/11/1 0001 12:02
 * 邮箱：xjs250@163.com
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.HeadViewHolder> {


    private List<Chat> list;
    private Context context;


    public ChatAdapter(List<Chat> list) {
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
                context.startActivity(IntentHelp.toChatActivity(list.get(position).getUserId()));
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(HeadViewHolder holder, int position) {

        Glide.with(context).load(GlobalData.HTTP_ADDRESS_PICTURE + list.get(position).getUserImageUrl()).into(holder.imageview);


        holder.chatName.setText(list.get(position).getUserName());
        holder.chatMessage.setText(list.get(position).getLastMessage());
        holder.chatTime.setText(list.get(position).getGetMsgTime());
        holder.pos = position;

            holder.messageNoRead.setVisibility(View.GONE);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HeadViewHolder extends RecyclerView.ViewHolder {
        int pos = 0;
        View view;
        CircleImageView imageview;
        TextView chatName;
        TextView chatMessage;
        TextView messageNoRead;
TextView chatTime;
        public HeadViewHolder(View itemView) {

            super(itemView);
            view = itemView;
            messageNoRead = (TextView) itemView.findViewById(R.id.unread_msg_number);
            imageview = (CircleImageView) itemView.findViewById(R.id.chat_image);
            chatName = (TextView) itemView.findViewById(R.id.chat_name);
            chatMessage = (TextView) itemView.findViewById(R.id.chat_message);
            chatTime=(TextView)itemView.findViewById(R.id.chat_time);
        }
    }
}
