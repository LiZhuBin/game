package com.example.administrator.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.thing_class.Msg;
import com.example.administrator.myapplication.util.ApplicationUtil;
import com.example.administrator.myapplication.util.GlobalData;

import java.util.List;

/**
 * Created by 10619 on 2017/9/21.
 */

public class MsgAdapter extends RecyclerView.Adapter <MsgAdapter.ViewHolder>{

    private List<Msg> mMsgList;

    static class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout leftLayout;
        FrameLayout rightLayout;
        ImageView otherImage;
        ImageView meImage;
        TextView leftMsg;
        TextView rightMsg;

        public ViewHolder(View view) {
            super(view);
            otherImage=(ImageView)view.findViewById(R.id.chat_image_other);
            meImage=(ImageView)view.findViewById(R.id.chat_image_me);
            leftLayout=(LinearLayout)view.findViewById(R.id.left_layout);
            rightLayout=(FrameLayout)view.findViewById(R.id.right_layout);
            leftMsg=(TextView)view.findViewById(R.id.left_msg);
            rightMsg=(TextView)view.findViewById(R.id.right_msg);
        }
    }

    public MsgAdapter(List<Msg> msgList) {
        mMsgList = msgList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.item_msg,parent,false);
        return new ViewHolder((view));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Msg msg=mMsgList.get(position);
        if(msg.getType()==Msg.TYPE_RECEIVED){
            //如果是收到的消息 ，则显示左边的消息布局，将右边的消息布局隐藏
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftMsg.setText(msg.getContent());
            Glide.with(ApplicationUtil.getContext()).load(GlobalData.httpAddressPicture+msg.getImageUrl()).into(holder.otherImage);
        }else if(msg.getType()==Msg.TYPE_SEND){
            //如果是发出的消息 ，则显示右边的消息布局，将左边的消息布局隐藏
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightMsg.setText(msg.getContent());
            Glide.with(ApplicationUtil.getContext()).load(GlobalData.httpAddressPicture+msg.getImageUrl()).into(holder.meImage);

        }
    }

    @Override
    public int getItemCount() {
        return mMsgList.size();
    }
}
