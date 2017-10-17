package com.example.administrator.happygame;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.administrator.happygame.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.moments.WechatMoments;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.administrator.happygame.util.ApplicationUtil.mobAccredit;

public class LoginActivity extends BaseActivity {


    @Bind(R.id.icon_image)
    CircleImageView iconImage;
    @Bind(R.id.email_edittext)
    EditText emailEdittext;
    @Bind(R.id.password_edittext)
    EditText passwordEdittext;
    @Bind(R.id.button)
    Button button;
    @Bind(R.id.share_wx)
    ImageView shareWx;
    @Bind(R.id.mRlWechat)
    RelativeLayout mRlWechat;
    @Bind(R.id.share_weibo)
    ImageView shareWeibo;
    @Bind(R.id.mRlWeibo)
    RelativeLayout mRlWeibo;
    @Bind(R.id.share_qq)
    ImageView shareQq;
    @Bind(R.id.mRlQQ)
    RelativeLayout mRlQQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent  != null &&  intent.getParcelableExtra("BITMAP") != null) {
            Bitmap bitmap = (Bitmap)getIntent().getParcelableExtra("BITMAP");
            iconImage.setImageBitmap(bitmap);
        }



    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        String str = emailEdittext.getText().toString();
        // Toast.makeText(this, str+"", Toast.LENGTH_SHORT).show();
        if (str.equals("lizhubin")) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick({R.id.share_wx, R.id.share_weibo, R.id.share_qq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.share_wx:
                Platform weixin = ShareSDK.getPlatform(WechatMoments.NAME);
                mobAccredit(weixin);
                break;
            case R.id.share_weibo:
                Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
                mobAccredit(weibo);
                break;
            case R.id.share_qq:
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                mobAccredit(qq);
                break;
        }
    }
}
