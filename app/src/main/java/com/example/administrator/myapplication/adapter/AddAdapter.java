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
import com.example.administrator.myapplication.activity.AddActivity;
import com.example.administrator.myapplication.thing_class.AddItem;
import com.example.administrator.myapplication.util.ApplicationUtil;
import com.example.administrator.myapplication.util.GlobalData;

import java.util.List;

/**
 * Created by Administrator on 2017/9/16 0016.
 */

public class AddAdapter  extends RecyclerView.Adapter<AddAdapter.ViewHolder> {
    private Context mContext;
    private List<AddItem> maddItem;
    public AddAdapter(List<AddItem> maddItem) {
        this.maddItem = maddItem;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext==null){
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.add_right_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.addView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position =holder.getAdapterPosition();
                Intent intent = new Intent(ApplicationUtil.getContext(), AddActivity.class);
                intent.putExtra("Object_userId",maddItem.get(position).getActivityId());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AddItem addItem=maddItem.get(position);
        Glide.with(ApplicationUtil.getContext()).load(GlobalData.httpAddressPicture+addItem.getImageId()).into(holder.forum_user_image);
        holder.forum_address.setText(addItem.getActivityAddress());
        holder.forum_content.setText(addItem.getActivityContent());
        holder.forum_title.setText(addItem.getActivityTitle());
        holder.forum_time.setText(addItem.getActivityTime());
        holder.forum_num.setText(addItem.getActivityNum());
    }

    @Override
    public int getItemCount() {
        return maddItem.size();
    }

    // Content View Elements


    // End Of Content View Elements
    static class ViewHolder extends RecyclerView.ViewHolder {
        View addView;
        public ImageView forum_user_image;

        public TextView forum_num;
        public TextView forum_title;
        public TextView forum_content;
        public TextView forum_time;
        public TextView forum_address;



        private ViewHolder(View view) {
            super(view);
            addView=view;
            forum_num=(TextView)view.findViewById(R.id.forum_num);
            forum_user_image = (ImageView) view.findViewById(R.id.forum_user_image);
            forum_title = (TextView) view.findViewById(R.id.forum_title1);
            forum_content = (TextView) view.findViewById(R.id.forum_content);
            forum_time = (TextView) view.findViewById(R.id.forum_time);
            forum_address = (TextView) view.findViewById(R.id.forum_address);
        }
    }


//    final LinearLayout layout2 = view.findViewById(R.id.new_user_linear_layout);
//    layout2.setOrientation(LinearLayout.HORIZONTAL);
//    add = (SuperButton) view.findViewById(R.id.new_content_add_button);
//    add.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            CircleImageButton myImageView=new CircleImageButton(view.getContext());
//            myImageView.setImageResource(R.drawable.image_whilte_me);
//            layout2.addView(myImageView);
//
//        }
//    });
//}

}
