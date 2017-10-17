package com.example.administrator.happygame.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.thing_class.Images;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2017/10/1 0001.
 */

public class Imageadapter extends RecyclerView.Adapter<Imageadapter.ViewHolder> {

    private Context mContext;
    private List<Images> mImages;
    private LayoutInflater mInflater;
    private Images toSelectImages;
    private Images previousImages;
    private ViewHolder previousHolder;

    public Imageadapter(Context context) {
        mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
    }

    public void RefreshLst(List<Images> list) {
        if (list != null) {
            mImages = list;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Images image = mImages.get(position);
        holder.pos = position;
        Log.d("baba", "loading");
        Glide.with(mContext).load(new File(image.getPath())).diskCacheStrategy(DiskCacheStrategy.NONE).centerCrop().into(holder.ivImage);
        ImageView imageView = holder.ivImage;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "" + mImages.get(position).getName(), Toast.LENGTH_SHORT).show();

                if (previousHolder != holder && previousHolder != null) {
                    previousHolder.isSelcet = false;
                    selectImage(false, previousHolder); //这里让上一个holder恢复原状
                }

                if (holder.isSelcet) {
                    selectImage(false, holder);
                    previousHolder = null;
                } else {
                    selectImage(true, holder);
                    previousHolder = holder;
                }//在这里完成 选中和没选中的状态切换
            }
        });


    }

    public void setmImages(List<Images> mImages) {
        this.mImages = mImages;
    }

    @Override
    public int getItemCount() {
        if (mImages == null)
            Log.d("baba", "Change" + (mImages == null ? 0 : mImages.size()));
        return mImages == null ? 0 : mImages.size();
    }

    public void selectImage(boolean tobeState, ViewHolder holder) {

        if (tobeState == false) //如果是切换到未被选中
        {
            holder.isSelcet = false; //原本是被选中的状态则转换为未被选中，同时恢复不透明
            holder.ivImage.setAlpha(1f);
            holder.isSelectButton.setVisibility(View.GONE);
        } else if (tobeState == true) {
            holder.ivImage.setAlpha(0.5f);
            holder.isSelcet = true;
            holder.isSelectButton.setVisibility(View.VISIBLE);
        }
    }

    public Images getPreviousImages() {
        //  Toast.makeText(mContext,"OK2",Toast.LENGTH_SHORT).show();

        //通过被挑选的holder来，确定该holder的pos，来获得被挑选images

        return previousHolder == null ? null : (mImages.get(previousHolder.pos));

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        int pos;
        ImageView ivImage;
        ImageView isSelectButton;
        boolean isSelcet = false;

        public ViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.imageitem);
            isSelectButton = (ImageView) itemView.findViewById(R.id.SelectButton);
        }
    }
}