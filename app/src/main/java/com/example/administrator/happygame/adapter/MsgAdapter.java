package com.example.administrator.happygame.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.thing_class.Msg;
import com.example.administrator.happygame.util.GlobalData;
import com.example.administrator.happygame.util.MyApplication;
import com.github.library.bubbleview.BubbleTextView;

import java.util.List;

/**
 * Created by 10619 on 2017/9/21.
 */

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {

    private List<Msg> mMsgList;

    public MsgAdapter(List<Msg> msgList) {
        mMsgList = msgList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.item_msg, parent, false);
        return new ViewHolder((view));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Msg msg = mMsgList.get(position);
        if (msg.getType() == Msg.TYPE_RECEIVED) {
            //如果是收到的消息 ，则显示左边的消息布局，将右边的消息布局隐藏
            holder.leftMsg.setText(msg.getContent());
            holder.rightMsg.setVisibility(View.GONE);
            holder.meImage.setVisibility(View.GONE);
            Glide.with(MyApplication.getContext()).load(GlobalData.HTTP_ADDRESS_PICTURE + msg.getImageUrl()).into(holder.otherImage);
        } else if (msg.getType() == Msg.TYPE_SEND) {
            //如果是发出的消息 ，则显示右边的消息布局，将左边的消息布局隐藏
            holder.rightMsg.setText(msg.getContent());
            holder.leftMsg.setVisibility(View.GONE);
            holder.otherImage.setVisibility(View.GONE);
            Glide.with(MyApplication.getContext()).load(GlobalData.HTTP_ADDRESS_PICTURE + msg.getImageUrl()).into(holder.meImage);

        }
    }

    @Override
    public int getItemCount() {
        return mMsgList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView otherImage;
        ImageView meImage;
        BubbleTextView leftMsg;
        BubbleTextView rightMsg;

        public ViewHolder(View view) {
            super(view);
            otherImage = (ImageView) view.findViewById(R.id.chat_image_other);
            meImage = (ImageView) view.findViewById(R.id.chat_image_me);

            leftMsg = (BubbleTextView) view.findViewById(R.id.left_msg);
            rightMsg = (BubbleTextView) view.findViewById(R.id.right_msg);
        }
    }
}
