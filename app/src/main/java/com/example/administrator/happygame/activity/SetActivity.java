package com.example.administrator.happygame.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.allen.library.SuperTextView;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.base.BaseActivity;
import com.example.administrator.happygame.onekeyshare.OnekeyShare;
import com.example.administrator.happygame.onekeyshare.ShareContentCustomizeCallback;
import com.example.administrator.happygame.util.GlobalData;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.tencent.qq.QQ;

public class SetActivity extends BaseActivity {


    @Bind(R.id.settings)
    SuperTextView settings;
    @Bind(R.id.about)
    SuperTextView about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        ButterKnife.bind(this);
    }


    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //通过OneKeyShareCallback来修改不同平台分享的内容
        oks.setShareContentCustomizeCallback(new ShareContentCustomizeDemo());
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");

        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl(GlobalData.HTTP_ADDRESS_PICTURE + "advertise/2457f0f5834ea526a9118b023ad6c00d.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }

    @OnClick({R.id.settings, R.id.about})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.settings:
                showShare();
                break;
            case R.id.about:
                startActivity(new Intent(SetActivity.this, AboutActivity.class));
                break;
            default:
                break;
        }
    }


    public class ShareContentCustomizeDemo implements ShareContentCustomizeCallback {

        @Override
        public void onShare(Platform platform, Platform.ShareParams paramsToShare) {
            // 改写twitter分享内容中的text字段，否则会超长，
            // 因为twitter会将图片地址当作文本的一部分去计算长度

            if (QQ.NAME.equals(platform.getName())) {
                paramsToShare.setText("ffff");

            }
        }

    }
}
