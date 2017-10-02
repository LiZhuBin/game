package com.example.administrator.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.my_ui.GlideRoundTransform;
import com.example.administrator.myapplication.thing_class.Images;
import com.makeramen.roundedimageview.RoundedImageView;

public class AddAddActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_add);
        Toolbar toolbar = (Toolbar)findViewById(R.id.newactivity_toolbar);
        setSupportActionBar(toolbar);
        ImageView imageView = (ImageView)findViewById(R.id.Back_button);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ImageView usePhotoImage = (ImageView)findViewById(R.id.add_chooseImage);
        usePhotoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddAddActivity.this,GetPhotoActivity.class);
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
                        Toast.makeText(AddAddActivity.this, "" + one.getName() , Toast.LENGTH_SHORT).show();
                        break;
                }
                break;
        }
    }

    public void setHeadview(String path)
    {
        //在这里去设置框的装饰！！！！！！！！！
        RoundedImageView imageView = (RoundedImageView)findViewById(R.id.add_chooseImage);
        imageView.setBackground(null);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setCornerRadius(20f);
        imageView.setBorderWidth(1f);
        imageView.setBorderColor(getResources().getColor(R.color.cpb_green));
        Glide.with(this).load(path).transform(new GlideRoundTransform(this,10)).into(imageView);

    }


}
