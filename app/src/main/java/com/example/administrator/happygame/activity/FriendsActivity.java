package com.example.administrator.happygame.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.administrator.happygame.R;
import com.example.administrator.happygame.adapter.FriendsAdapter;
import com.example.administrator.happygame.base.BaseActivity;
import com.example.administrator.happygame.been.User;
import com.example.administrator.happygame.thing_class.Friends;
import com.example.administrator.happygame.util.GlobalData;
import com.example.administrator.happygame.util.HttpUtil;
import com.example.administrator.happygame.util.IntentHelp;
import com.example.administrator.happygame.util.MyApplication;
import com.example.administrator.happygame.util.StringUtil;
import com.jaouan.revealator.Revealator;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class FriendsActivity extends BaseActivity {
    private static List<Friends> friends = new ArrayList<Friends>();
    Toolbar toolbar;
    String title;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    FriendsAdapter adapter = new FriendsAdapter(friends);
                    RecyclerView listview = (RecyclerView) findViewById(R.id.list_view);

                    listview.setAdapter(adapter);
                    GridLayoutManager manager = new GridLayoutManager(MyApplication.getContext(), 3);
                    listview.setLayoutManager(manager);
                    break;
                default:
                    break;
            }
        }
    };
    @Bind(R.id.toolbar_text)
    TextView toolbarText;
    @Bind(R.id.btn_add)
    Button btnAdd;
    @Bind(R.id.item_edit_text)
    TextView itemEditText;
    @Bind(R.id.item_edit)
    EditText itemEdit;
    @Bind(R.id.edit_ensure)
    Button editEnsure;
    @Bind(R.id.edit_frame)
    FrameLayout editFrame;

    private String[] friendsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        ButterKnife.bind(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar_edit);
        // getSupportActionBar().hide();
        title = getIntent().getExtras().getString("title");
        toolbarText.setText(title);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        itemEditText.setText("输入用户名");
        initFriends();
    }

    private void initFriends() {     //初始化朋友数据

        friendsId = StringUtil.httpArray(getIntent().getExtras().getString("friends"));

        getFriends(friendsId);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getFriends(String[] friendsId) {
        friends.clear();
        for (int i = 0; i < friendsId.length; i++) {

            HttpUtil.sendOkHttpResquest(GlobalData.HTTP_ADDRESS_USER + "php/getById.php", friendsId[i], new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    User user = HttpUtil.getSingleUser(response);
                    Message message = handler.obtainMessage();
                    message.what = 1;
                    handler.sendMessage(message);
                    friends.add(new Friends(user.getId(), user.getName(), user.getImage()));

                }
            });
        }
    }



    @OnClick({R.id.btn_add,R.id.edit_ensure, R.id.edit_frame})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                Revealator.reveal(editFrame)
                        .from(btnAdd)
                        .withCurvedTranslation()
                        .withChildsAnimation()
                        .start();

                break;
            case R.id.edit_ensure:
                Revealator.unreveal(editFrame)
                        .to(btnAdd)
                        .withCurvedTranslation()
                        .start()
                ;
                EventBus.getDefault().post(
                        itemEditText.getText().toString());
                startActivity(IntentHelp.toSearchActivity(itemEditText.getText().toString()));
                break;
            case R.id.edit_frame:
                break;
                default:
                    break;
        }
    }
}


