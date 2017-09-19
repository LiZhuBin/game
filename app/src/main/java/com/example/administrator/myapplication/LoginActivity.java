package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.myapplication.util.StringUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.icon_image)
    CircleImageView iconImage;
    @Bind(R.id.email_edittext)
    EditText emailEdittext;
    @Bind(R.id.password_edittext)
    EditText passwordEdittext;

    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.button)
    Button button;
    private static final String TAG = "LoginActivity";
    String friendsId[] =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        String getData=savedInstanceState.getString("friends");
        Toast.makeText(this, getData+"", Toast.LENGTH_SHORT).show();
        friendsId= StringUtil.httpArray(getData);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        String str=emailEdittext.getText().toString();
       // Toast.makeText(this, str+"", Toast.LENGTH_SHORT).show();
        if(str=="lizhubin"){
            startActivity(new Intent(this,MainActivity.class));
        }else {
            Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
        }

    }
}
