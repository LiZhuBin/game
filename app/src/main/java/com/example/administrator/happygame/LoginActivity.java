package com.example.administrator.happygame;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.administrator.happygame.activity.RegisterActivity;
import com.example.administrator.happygame.base.BaseActivity;
import com.example.administrator.happygame.been.User;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.wechat.moments.WechatMoments;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.administrator.happygame.util.GlobalData.mUserDao;
import static com.example.administrator.happygame.util.GlobalData.qq;

public class LoginActivity extends BaseActivity {


    @Bind(R.id.icon_image)
    CircleImageView iconImage;
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
    @Bind(R.id.login_register)
    TextView loginRegister;
    @Bind(R.id.title)
    LinearLayout title;
    @Bind(R.id.login_input_name)
    EditText loginInputName;
    @Bind(R.id.login_input_password)
    EditText loginInputPassword;
    @Bind(R.id.textView2)
    TextView textView2;
    @Bind(R.id.login_edit_layout)
    LinearLayout loginEditLayout;
    @Bind(R.id.login_btn)
    Button loginBtn;
    @Bind(R.id.login_scroller)
    ScrollView loginScroller;
  public  static   Platform platform=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        if (qq.isAuthValid()) {
            //开始qq授权登录
           startActivity(new Intent(this,MainActivity.class));
        }
        Intent intent = getIntent();
        if (intent != null && intent.getParcelableExtra("BITMAP") != null) {
            Bitmap bitmap = (Bitmap) getIntent().getParcelableExtra("BITMAP");
            iconImage.setImageBitmap(bitmap);
        }


    }


    @OnClick({R.id.share_wx, R.id.share_weibo, R.id.share_qq,R.id.login_register, R.id.login_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.login_btn:

                for(User user:mUserDao.loadAll()){
                    if (loginInputName.getText().toString().equals(user.getName())){
                        if (loginInputName.getText().toString().equals(user.getPassword())) {
                            startActivity(new Intent(this, MainActivity.class));
                        }
                    }
                }
                break;
            case R.id.share_wx:
                Platform weixin = ShareSDK.getPlatform(WechatMoments.NAME);
                mobAccredit(weixin);
                break;
            case R.id.share_weibo:
                Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
                mobAccredit(weibo);
                break;
            case R.id.share_qq:

                mobAccredit(qq);

                break;
            default:
                break;
        }

    }
    public void mobAccredit(Platform platform) {
LoginActivity.platform =platform;
//回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
        platform.setPlatformActionListener(new PlatformActionListener() {

            @Override
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                // TODO Auto-generated method stub
                arg2.printStackTrace();
            }

            @Override
            public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                // TODO Auto-generated method stub
                //输出所有授权信息
                arg0.getDb().exportData();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }

            @Override
            public void onCancel(Platform arg0, int arg1) {
                // TODO Auto-generated method stub

            }
        });
        platform.authorize();//单独授权,OnComplete返回的hashmap是空的
        platform.showUser(null);//授权并获取用户信息
    }
}
