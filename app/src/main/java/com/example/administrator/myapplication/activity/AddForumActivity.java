package com.example.administrator.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.my_ui.GlideRoundTransform;
import com.example.administrator.myapplication.thing_class.Images;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddForumActivity extends BaseActivity {

    @Bind(R.id.activity_toolbar)
    Toolbar activityToolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.activity_add_forum_title_edittext)
    EditText activityAddForumTitleEdittext;
    @Bind(R.id.activity_add_forum_type_edittext)
    EditText activityAddForumTypeEdittext;
    @Bind(R.id.activity_add_forum_content_edittext)
    EditText activityAddForumContentEdittext;
    @Bind(R.id.forum_chooseImage)
    RoundedImageView forumChooseImage;
    @Bind(R.id.activity_add_forum_send_button)
    Button activityAddForumSendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_forum);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_toolbar);
        // getSupportActionBar().hide();
        setSupportActionBar(toolbar);
        ImageView imageView = (ImageView) findViewById(R.id.Back_button);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        forumChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddForumActivity.this,GetPhotoActivity.class);
                startActivityForResult(intent,2);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode)
        {
            case 2 :
                switch (resultCode)
                {
                    case 1 :
                        Images one = (Images) data.getSerializableExtra("Return_images");
                        setHeadview(one.getPath()); //这里去获得返回来的数据 地址！然后获得图片
                        Toast.makeText(AddForumActivity.this, "" + one.getName() , Toast.LENGTH_SHORT).show();
                        break;
                }
                break;
        }
    }

    public void setHeadview(String path)
    {
        //在这里去设置框的装饰！！！！！！！！！
        RoundedImageView imageView = (RoundedImageView)findViewById(R.id.forum_chooseImage);
        imageView.setBackground(null);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setCornerRadius(20f);
        imageView.setBorderWidth(1f);
        imageView.setBorderColor(getResources().getColor(R.color.cpb_green));
        Glide.with(this).load(path).transform(new GlideRoundTransform(this,10)).into(imageView);

    }
}
