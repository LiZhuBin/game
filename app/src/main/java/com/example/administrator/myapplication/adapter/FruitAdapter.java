package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.thing_class.Fruit;

import java.util.List;

/**
 * Created by Administrator on 2017/8/18 0018.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder>{
    Context mcontext;
    private List<Fruit> mFruitList;
    public FruitAdapter(List<Fruit> list){
        mFruitList=list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mcontext==null){
            mcontext=parent.getContext();
        }
        View view= LayoutInflater.from(mcontext).inflate(R.layout.fruit_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int position=holder.getAdapterPosition();
                Fruit fruit=mFruitList.get(position);
//                Intent intent=new Intent(mcontext, ScrollingActivity.class);
//                intent.putExtra(ScrollingActivity.POSITION,position);
//                intent.putExtra(ScrollingActivity.IMAGE_ID,fruit.getImageId());
//                mcontext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit=mFruitList.get(position);
        holder.fruitName.setText(fruit.getName());
        Glide.with(mcontext).load(fruit.getImageId()).into(holder.fruitImage);
    }


    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(View view){
            super(view);
            cardView=(CardView)view;
            fruitImage=(ImageView)view.findViewById(R.id.fruit_image);
            fruitName=(TextView)view.findViewById(R.id.fruit_name);
        }
    }
}