package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.thing_class.Friends;

import java.util.List;

/**
 * Created by 10619 on 2017/9/11.
 */

public class FriendsAdapter extends ArrayAdapter<Friends> {

    private int resourceId;

    public FriendsAdapter(Context context, int textViewResourceId, List<Friends> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Friends friends=getItem(position);//获取当前项的Friends实例
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.friendsImage=(ImageView)view.findViewById(R.id.friends_image);
            viewHolder.friendsName=(TextView)view.findViewById(R.id.friends_name);
            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();
        }
        viewHolder.friendsImage.setImageResource(friends.getImageId());
        viewHolder.friendsName.setText(friends.getName());
        return view;
    }

    class ViewHolder{
        ImageView friendsImage;

        TextView friendsName;
    }

}
