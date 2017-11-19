package com.example.administrator.happygame.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.activity.fifth.CameraFragmentMainActivity;
import com.example.administrator.happygame.activity.fifth.EditActivity;
import com.example.administrator.happygame.base.BaseActivity;
import com.example.administrator.happygame.been.User;
import com.example.administrator.happygame.my_ui.CreditView;
import com.example.administrator.happygame.thing_class.Images;
import com.example.administrator.happygame.util.GlobalData;
import com.example.administrator.happygame.util.HttpUtil;
import com.example.administrator.happygame.util.IntentHelp;
import com.example.administrator.happygame.util.LogUtil;
import com.example.administrator.happygame.util.MyApplication;
import com.jaouan.revealator.Revealator;
import com.lqr.optionitemview.OptionItemView;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.duduhuo.dialog.smartisan.SmartisanDialog;
import cc.duduhuo.dialog.smartisan.TwoOptionsDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MyInformationActivity extends BaseActivity {


    @Bind(R.id.ivHeaderImage)
    ImageView ivHeaderImage;
    @Bind(R.id.toolbar_edit)
    Toolbar toolbar;
    @Bind(R.id.llHeader)
    LinearLayout llHeader;
    @Bind(R.id.oivName)
    OptionItemView oivName;
    @Bind(R.id.oivCredit)
    OptionItemView oivCredit;
    @Bind(R.id.oivSix)
    OptionItemView oivSix;
    @Bind(R.id.dashboard_view_2)
    CreditView dashboardView2;
    TwoOptionsDialog dialog;


    @Bind(R.id.oivLocation)
    OptionItemView oivLocation;
    @Bind(R.id.oivSignature)
    OptionItemView oivSignature;
    User user;
    @Bind(R.id.credit_frame)
    FrameLayout creditFrame;
    @Bind(R.id.ivHeaderZxing)
    ImageView ivHeaderZxing;
    @Bind(R.id.zxing_layout)
    LinearLayout zxingLayout;
    @Bind(R.id.toolbar_text)
    TextView toolbarText;
    @Bind(R.id.btn_add)
    Button btnAdd;
    Images one;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    oivSix.setRightText("男");
                    user.setSix("男");
                    break;
                case 1:
                    oivSix.setRightText("女");
                    user.setSix("女");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_information);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent != null && intent.getParcelableExtra("BITMAP") != null) {
            Bitmap bitmap = (Bitmap) getIntent().getParcelableExtra("BITMAP");
            ivHeaderImage.setImageBitmap(bitmap);

            user = (User) getIntent().getParcelableExtra("USER");
            final Bitmap zxingBitmap = CodeUtils.createImage(user.getId(), 400, 400, bitmap);


            ivHeaderZxing.setImageBitmap(zxingBitmap);
        }
        initData();
        dialog = SmartisanDialog.createTwoOptionsDialog(this);
        toolbarText.setText("个人信息");
        setSupportActionBar(toolbar);
        btnAdd.setText("确定");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @OnClick({R.id.llHeader, R.id.oivName, R.id.oivCredit, R.id.oivSix, R.id.oivSignature, R.id.dashboard_view_2, R.id.credit_frame, R.id.zxing_layout,R.id.btn_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.llHeader:

                dialog.setTitle("请选择")
                        .setOp1Text("拍照")   // 设置第一个选项的文本
                        .setOp2Text("从相册中选择")// 设置第二个选项的文本
                        .show();
                dialog.setOnSelectListener(new TwoOptionsDialog.OnSelectListener() {
                    @Override
                    public void onOp1() {
                        dialog.dismiss();
                        Intent intent = new Intent(MyApplication.getContext(), CameraFragmentMainActivity.class);
                        startActivityForResult(intent, 2);
                    }

                    @Override
                    public void onOp2() {
                        dialog.dismiss();
                        Intent intent = new Intent(MyApplication.getContext(), GetPhotoActivity.class);
                        startActivityForResult(intent, 2);
                    }
                });
                break;
            case R.id.oivName:
                Intent intent1 = new Intent(MyApplication.getContext(), EditActivity.class);
                intent1.putExtra("type", "name");
                intent1.putExtra("DATA",user.getName());
                startActivityForResult(intent1, 2);

                break;
            case R.id.oivCredit:
                Revealator.reveal(creditFrame)
                        .from(oivCredit)
                        .withCurvedTranslation()
                        .withChildsAnimation()
                        .start();
                dashboardView2.setCreditValueWithAnim((Integer.parseInt(user.getCredit())));
                break;
            case R.id.zxing_layout:
                startActivity(IntentHelp.toZxingActivity(ivHeaderImage));
                break;
            case R.id.oivSix:
                dialog.setTitle("请选择性别")
                        .setOp1Text("男")   // 设置第一个选项的文本
                        .setOp2Text("女")   // 设置第二个选项的文本
                        .show();
                dialog.setOnSelectListener(new TwoOptionsDialog.OnSelectListener() {
                    @Override
                    public void onOp1() {
                        dialog.dismiss();
                        Message message = handler.obtainMessage();
                        message.what = 0;
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onOp2() {
                        dialog.dismiss();
                        Message message = handler.obtainMessage();
                        message.what = 1;
                        handler.sendMessage(message);
                    }
                });
                break;
            case R.id.oivSignature:
                Intent intent2 = new Intent(MyApplication.getContext(), EditActivity.class);
                intent2.putExtra("type", "signature");
                intent2.putExtra("DATA",user.getSignature());
                startActivityForResult(intent2, 2);
                break;
            case R.id.dashboard_view_2:
                Revealator.unreveal(creditFrame)
                        .to(oivCredit)
                        .withCurvedTranslation()
                        .start();
                break;
            case  R.id.btn_add:

               String emailAddress = HttpUtil.getJson(user);
                LogUtil.e(emailAddress);


                HttpUtil.sendOkHttpResquest(GlobalData.HTTP_ADDRESS_USER+ "php/UpdateDataForUser.php", "[" + HttpUtil.getJson(user) + "]", new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                            LogUtil.e(response.toString());
                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {

            case 2:
                switch (resultCode) {
                    case 1:
                        one = (Images) data.getSerializableExtra("Return_images");
                        Glide.with(MyInformationActivity.this).load(one.getPath()).into(ivHeaderImage);
                        //这里去获得返回来的数据 地址！然后获得图片
                        StringBuilder stringBuilder = new StringBuilder("user/" );
                        if (one != null) {
                            stringBuilder.append(one.getName()).toString();
                        } else {
                            stringBuilder.append("picture1.jpg");
                        }
                        user.setImage(stringBuilder.toString());
                        break;
                    case 2:

                        String name = data.getStringExtra("data");
                        user.setName(name);

                        initData();
                        break;
                    case 3:
                        String credit = data.getStringExtra("data");
                        user.setSignature(credit);
                        initData();
                        break;
                    default:
                        break;
                }

                break;
            default:
                break;
        }
    }

    public void initData() {
        oivName.setRightText(user.getName());
        oivCredit.setRightText(user.getCredit());
        oivSix.setRightText(GlobalData.getStringSix(user.getSix()));
        oivLocation.setRightText(user.getAddress());
        oivSignature.setRightText(user.getSignature());
    }
}
