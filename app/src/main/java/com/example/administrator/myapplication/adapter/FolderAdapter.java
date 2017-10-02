package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.activity.GetPhotoActivity;
import com.example.administrator.myapplication.thing_class.Folder;
import com.example.administrator.myapplication.thing_class.Images;

import java.util.List;

/**
 * Created by Administrator on 2017/10/1 0001.
 */

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.ViewHolder> {

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    private boolean isOpen = false ;
    private Context Mycontext ;
    private List<Folder> folders ;
    private Button changeButton ;
    private Imageadapter CallImageadapter ;
    private ViewHolder PreChooseFolderhold;
    private TextView   folderTextview  ;




    public FolderAdapter(Context context, Imageadapter imageadapter)
    {
        Mycontext = context ;
        CallImageadapter = imageadapter ;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (Mycontext==null){ Mycontext = parent.getContext(); }
       /* if (changeButton==null){ changeButton = (Button) parent.findViewById(R.id.Button_complete);
            changeButton.setText(folders.get(0).getName());
        }*/


        if (folderTextview==null)
        {
            folderTextview=(TextView) parent.getRootView().findViewById(R.id.Button_complete);
        }

        View view = LayoutInflater.from(Mycontext).inflate(R.layout.item_folder,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (PreChooseFolderhold==null&&position==0)
        {
            //第一次进入时候，pre默认为第一个，同时textview也是
            PreChooseFolderhold = holder;
            holder.Folder_bechooseicon.setVisibility(View.VISIBLE);

            folderTextview.setText(folders.get(0).getName());
        }


        final Folder one =  folders.get(position);
        holder.FolderName.setText(one.getName());
        Glide.with(Mycontext).load(one.getImageList().get(0).getPath()).centerCrop().into(holder.Folder_Firstphoto);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击首先让上一个被选择的folder的选择图标消失
                PreChooseFolderhold.Folder_bechooseicon.setVisibility(View.GONE);
                //再让现在被点击的图标出现
                holder.Folder_bechooseicon.setVisibility(View.VISIBLE);
                //同时获得被点击的folder类
                List<Images> toChangeList = one.getImageList();
                //回调主程序，让overflow下降
                ((GetPhotoActivity)Mycontext).setShowRecyclerview(false,((GetPhotoActivity)Mycontext).recyclerView1);
                //更新显示着图片的recyclerview，让adapter察觉到数据改变
                CallImageadapter.RefreshLst(toChangeList);
                CallImageadapter.notifyDataSetChanged();
                //让这次点击的folder默认为上一个被点击的folder
                PreChooseFolderhold = holder ;
                //把点击后的文件夹名字赋予给textivew
                folderTextview.setText(one.getName());

            }
        });


    }



    @Override
    public int getItemCount()
    {
        if (folders==null)
        {
            Log.d("OKA","0000");
        }
        else{ Log.d("OKA","111"+folders.size());
            //  Toast.makeText(Mycontext,"??",Toast.LENGTH_LONG).show();
        }

        return  folders==null?0:folders.size() ;
    }

    public void setfolders(List<Folder> one){
        folders = one ;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView FolderName ;
        ImageView Folder_Firstphoto ;
        ImageView Folder_bechooseicon ;

        public ViewHolder(View itemView) {
            super(itemView);
            FolderName = (TextView)itemView.findViewById(R.id.folder_NameTextview);
            Folder_Firstphoto = (ImageView)itemView.findViewById(R.id.folder_firstImageview);
            Folder_bechooseicon = (ImageView)itemView.findViewById(R.id.folder_bechooseicon);
        }
    }

}

