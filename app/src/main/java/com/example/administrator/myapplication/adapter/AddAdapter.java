package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.my_ui.AddItem;

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

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AddItem addItem=maddItem.get(position);
        holder.forum_user_image.setImageResource(addItem.getImageId());
        holder.forum_user_name.setText(addItem.getForumUserName());
        holder.forum_address.setText(addItem.getForumAddress());
        holder.time.setText(addItem.getTime());
        holder.forum_content.setText(addItem.getForumContent());
        holder.forum_title.setText(addItem.getForumTitle());
        holder.forum_time.setText(addItem.getForumTime());
    }

    @Override
    public int getItemCount() {
        return maddItem.size();
    }

    // Content View Elements


    // End Of Content View Elements
    static class ViewHolder extends RecyclerView.ViewHolder {
        View addView;
        public de.hdodenhof.circleimageview.CircleImageView forum_user_image;
        public TextView forum_user_name;
        public TextView time;
        public TextView forum_num;
        public TextView forum_title;
        public TextView forum_content;
        public TextView forum_time;
        public TextView forum_address;



        private ViewHolder(View view) {
            super(view);
            addView=view;
            forum_user_image = (de.hdodenhof.circleimageview.CircleImageView) view.findViewById(R.id.forum_user_image);
            forum_user_name = (TextView) view.findViewById(R.id.forum_user_name);
            time = (TextView) view.findViewById(R.id.time);
            forum_num = (TextView) view.findViewById(R.id.forum_num);
            forum_title = (TextView) view.findViewById(R.id.forum_title);
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
