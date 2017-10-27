package com.example.administrator.happygame.activity;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.adapter.FolderAdapter;
import com.example.administrator.happygame.adapter.Imageadapter;
import com.example.administrator.happygame.base.BaseActivity;
import com.example.administrator.happygame.thing_class.Folder;
import com.example.administrator.happygame.thing_class.Images;
import com.example.administrator.happygame.thing_class.folderitemdecoration;
import com.example.administrator.happygame.thing_class.lookphotoitemdecorate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetPhotoActivity extends BaseActivity {
    private final int ResultCode = 1;
    private final int Open_Photomap = 2;
    private final int Request_OpenPhotoMap = 1;
    public RecyclerView recyclerView1; //这个扩展菜单的recylerview
    private Uri ImageUri;
    private ImageView imageView;
    private List<Images> imageslist;
    private boolean isOver = false;
    private List<Folder> folderlist;
    private Imageadapter imageadapter;
    private FolderAdapter folder_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_photo);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        } else {
            getAllPhoto();  //这里完成读写系统所有图片文件的操作！！
        }

        initSelectPhoto(); //这里把标题栏要的东西给设定好了，比如 点击完成返回 Images类给请求地方！！

        //这里是第一层图片，只要遍历完就可以知道有多少个文件夹了

        initShowImageview();
        initFolderOverflowRecyclerview();


        while (true) {
            if (isOver) {
                // imageadapter.setmImages(imageslist);
                folder_adapter.setfolders(folderlist); //在完成之后，就可以设置文件夹目录
                //imageadapter.notifyDataSetChanged();
                //a.notifyDataSetChanged();
                //从这里如果执行完毕
                break;
            }
        }
        //这里开始处理左下角

    }

    private void initShowImageview() {
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecyclerView_photoshow);
        GridLayoutManager myLinearlayoutManger = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(myLinearlayoutManger);
        imageadapter = new Imageadapter(this);
        recyclerView.setAdapter(imageadapter);
        recyclerView.addItemDecoration(new lookphotoitemdecorate(getApplicationContext()));

    }

    private void initFolderOverflowRecyclerview() {
        folder_adapter = new FolderAdapter(this, imageadapter);
        TextView buttom = (TextView) findViewById(R.id.Button_complete);
        recyclerView1 = (RecyclerView) findViewById(R.id.overflowRecyclerview);
        recyclerView1.setBackgroundColor(Color.WHITE);
        GridLayoutManager myLinearlayoutManger1 = new GridLayoutManager(this, 1);
        recyclerView1.setLayoutManager(myLinearlayoutManger1);
        recyclerView1.setAdapter(folder_adapter);
        recyclerView1.addItemDecoration(new folderitemdecoration(getApplicationContext()));
        recyclerView1.post(new Runnable() {
            @Override
            public void run() {
                //这个方法保证在 onlayout后执行，所以获得了 布局宽度
                recyclerView1.setTranslationY(recyclerView1.getHeight());
                recyclerView1.setVisibility(View.GONE);
            }
        });

        View view = (View) findViewById(R.id.View1);
        view.setVisibility(View.GONE);//这个黑色挡板一开始是不存在的
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setShowRecyclerview(false, recyclerView1);
            }
        });

        buttom.setOnClickListener(new View.OnClickListener() {
            Boolean isOpen = false;
            Boolean isFirst = true;

            @Override
            public void onClick(View v) {
                boolean isOpen = folder_adapter.isOpen();
                setShowRecyclerview(!isOpen, recyclerView1);
            }
        });
    }


    public void setShowRecyclerview(Boolean isOpen, final RecyclerView recyclerView1) {
        final View view = (View) findViewById(R.id.View1);

        if (isOpen) { //打开菜单
            ((FolderAdapter) recyclerView1.getAdapter()).setOpen(true);
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(recyclerView1, "translationY", (float) recyclerView1.getHeight(), 0f); //这是向上移动，就是打开菜单
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    //在动画开始前让Recycler存在
                    view.setVisibility(View.INVISIBLE);
                    recyclerView1.setVisibility(View.VISIBLE);
                    super.onAnimationStart(animation);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    view.setVisibility(View.VISIBLE);
                    super.onAnimationEnd(animation);
                }
            });
            objectAnimator.setDuration(400);
            objectAnimator.start();
        } else {
            ((FolderAdapter) recyclerView1.getAdapter()).setOpen(false);
            Toast.makeText(GetPhotoActivity.this, "OK2", Toast.LENGTH_SHORT).show();
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(recyclerView1, "translationY", 0f, (float) recyclerView1.getHeight());
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) { //在关闭动画开始的时候让他（完全透明可以用invisibility）！！
                    view.setVisibility(View.INVISIBLE);
                    super.onAnimationStart(animation);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    view.setVisibility(View.GONE);
                    recyclerView1.setVisibility(View.GONE);
                    super.onAnimationEnd(animation);
                }
            });
            objectAnimator.setDuration(400);
            objectAnimator.start();
            isOpen = false;
        }
    }

    private void initSelectPhoto() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.getphoto_toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button complete = (Button) findViewById(R.id.completeButton);
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Images choseImages = imageadapter.getPreviousImages();
                if (choseImages != null) {
                    //只要返回的image不为空就可以获得该images了
                    //从这里就可以返回 images给原本的活动了
                    Intent intent = new Intent();
                    intent.putExtra("Return_images", choseImages);
                    GetPhotoActivity.this.setResult(1, intent);
                    GetPhotoActivity.this.finish();
                }
            }
        });
    }


    private void startOpenPhotoMap() {

        if (ContextCompat.checkSelfPermission(GetPhotoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(GetPhotoActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Request_OpenPhotoMap);
        } else {

            openPhotoMap();
        }
    }

    private void openPhotoMap() {

        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, Open_Photomap);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                //Intent intent = new Intent(GetPhotoActivity.this,StartActivity2.class);
                Intent intent = new Intent();
                intent.putExtra("Result", "123");
                GetPhotoActivity.this.setResult(3, intent);
                GetPhotoActivity.this.finish();
                break;
            default:
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    private void Use_takePhoto() {
        File outputImage = new File(getExternalCacheDir(), "Photo1.jpg");

        try {
            if (outputImage.exists()) {
                outputImage.delete(); //如果有图片在里面就先删除
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            ImageUri = FileProvider.getUriForFile(GetPhotoActivity.this, "com.example.listview.fileprovider", outputImage); //在安卓24后面的内容uri都基本要通过加密,这样更安全1
        } else {
            ImageUri = Uri.fromFile(outputImage);//24前直接获取
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");//图片获取
        intent.putExtra(MediaStore.EXTRA_OUTPUT, ImageUri);
        startActivityForResult(intent, ResultCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ResultCode:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(ImageUri));
                        //打开输入流，从imageURL作输入流
                        imageView.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case Open_Photomap:
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        handleImageInSelect(data);
                    } else {
                        handldImageDirect(data);
                    }
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handldImageDirect(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void handleImageInSelect(Intent data) {
        Log.d("Saaa", "do it4");
        Uri uri = data.getData();
        String imageTruePath = null;

        Glide.with(this).load(uri).into(imageView);
        return;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case Request_OpenPhotoMap:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("baba", "111");
                    openPhotoMap();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_LONG).show();
                }
                break;
            case 2:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getAllPhoto();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_LONG).show();
                }

            default:
                break;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public String getImagePath(Uri uri, String selection) {
        Log.d("Saaa", "do it5");
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }

        return path;  //获得该图片的真实位置，同时这个位置没有被封装

    }

    private void setImageview(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            imageView.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "Not Find The Photo for this path", Toast.LENGTH_LONG).show();
        }
    }

    public void getAllPhoto() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI; //这是相册问文件
                ContentResolver mContentResover = getApplicationContext().getContentResolver();
                Cursor mcursor = mContentResover.query(uri, new String[]{MediaStore.Images.Media.DATA, MediaStore.Images.Media.DISPLAY_NAME, MediaStore.Images.Media.DATE_ADDED, MediaStore.Images.Media._ID},
                        null, null, MediaStore.Images.Media.DATE_ADDED); //最后一个是排序方式，应该根据时间来排序
                imageslist = new ArrayList<>();
                if (mcursor != null) {
                    while (mcursor.moveToNext()) {
                        String path = mcursor.getString(mcursor.getColumnIndex(MediaStore.Images.Media.DATA));
                        String name = mcursor.getString(mcursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
                        long time = mcursor.getLong(mcursor.getColumnIndex(MediaStore.Images.Media.DATE_ADDED));
                        imageslist.add(new Images(path, time, name));
                        if (imageadapter != null) {
                            imageadapter.setmImages(imageslist); //这里动态载入图片
                        }
                    }
                }
                //从这里挑选图片 分到不同的地方去
                SortImagesToFolder(imageslist);
                isOver = true;
                mcursor.close();
            }
        }).start();
    }

    private void SortImagesToFolder(List<Images> images) {
        //首先把所有图片给放到“全部图片的这个文件去”
        folderlist = new ArrayList<>();
        Folder All = new Folder("全部图片", images);
        folderlist.add(All);
        //把第一个全部图片搞定了就开始分类添加，遍历每一张图片
        for (int i = 0; i < images.size(); i++) {
            String path = images.get(i).getPath(); //获得path
            String folder_name = getFolderName(path);
            Images image = images.get(i);
            toSetFolder(folder_name, image);
        }
    }

    private void toSetFolder(String Foldername, Images target) {
        //去遍历现在有的floder看有没有重名，有就添加进去，没有就增加一个
        for (Folder one : folderlist) {
            if (one.getName().equals(Foldername)) {
                one.getImageList().add(target);
                return; //一有重名就添加走
            }
        }

        List<Images> newone = new ArrayList<>();
        newone.add(target);
        Log.d("OKA", "do");
        folderlist.add(new Folder(Foldername, newone));

    }

    private String getFolderName(String path) {

        if (path != null) {
            String[] debris = path.split(File.separator); //separator 分离器
            if (debris.length >= 2) {
                return debris[debris.length - 2];//长度-2
            }
        }
        return "";
    }

}
