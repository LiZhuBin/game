package com.example.administrator.happygame.main_fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.alimuzaffar.lib.widgets.AnimatedEditText;
import com.bumptech.glide.Glide;
import com.example.administrator.happygame.PoiIntent;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.activity.GetPhotoActivity;
import com.example.administrator.happygame.activity.fifth.CameraFragmentMainActivity;
import com.example.administrator.happygame.activity.fifth.MapActivity;
import com.example.administrator.happygame.adapter.AddAdapter;
import com.example.administrator.happygame.adapter.ViewPagerAdapter;
import com.example.administrator.happygame.base.BaseFragment;
import com.example.administrator.happygame.been.Activity;
import com.example.administrator.happygame.my_ui.GlideRoundTransform;
import com.example.administrator.happygame.thing_class.AddItem;
import com.example.administrator.happygame.thing_class.Images;
import com.example.administrator.happygame.util.ClasstoItem;
import com.example.administrator.happygame.util.GlobalData;
import com.example.administrator.happygame.util.HttpUtil;
import com.example.administrator.happygame.util.LogUtil;
import com.example.administrator.happygame.util.MyApplication;
import com.example.administrator.happygame.util.TimeUtil;
import com.example.administrator.happygame.util.UiUtil;
import com.google.gson.annotations.SerializedName;
import com.jaouan.revealator.Revealator;
import com.makeramen.roundedimageview.RoundedImageView;
import com.melnykov.fab.FloatingActionButton;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sdsmdg.tastytoast.TastyToast;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.duduhuo.dialog.smartisan.SmartisanDialog;
import cc.duduhuo.dialog.smartisan.TwoOptionsDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;
import static com.example.administrator.happygame.util.GlobalData.initActivityData;
import static com.example.administrator.happygame.util.GlobalData.mActivityDao;


public class AddFragment extends BaseFragment {
    protected WeakReference<View> mRootView;//缓存fragment数据
    protected ViewPagerAdapter adapter;
    @Bind(R.id.add_right_fragment_list)
    RecyclerView addRightFragmentList;
    @Bind(R.id.activity_add_forum_title_edittext)
    AnimatedEditText activityAddForumTitleEdittext;
    @Bind(R.id.activity_add_forum_place_edittext)
    AnimatedEditText activityAddForumPlaceEdittext;
    @Bind(R.id.activity_add_forum_content_edittext)
    AnimatedEditText activityAddForumContentEdittext;
    @Bind(R.id.add_refreshLayout)
    SmartRefreshLayout addRefreshLayout;
    @Bind(R.id.add)
    LinearLayout add;
    RecyclerView recyclerView;
    @Bind(R.id.the_awesome_view)
    FrameLayout theAwesomeView;
    @Bind(R.id.activity_add_forum_send_button)
    Button activityAddForumSendButton;
    @Bind(R.id.add_left)
    Button addLeft;
    @Bind(R.id.fab_add)
    FloatingActionButton fab;
    @Bind(R.id.select_date)
    Button selectDate;
    @Bind(R.id.datepicker_layout)
    FrameLayout datepickerLayout;
    @Bind(R.id.get_date)
    Button getDate;
    @Bind(R.id.DatePicker)
    android.widget.DatePicker DatePicker;
    @Bind(R.id.spinner)
    Spinner spinner;
    @Bind(R.id.add_chooseImage)
    RoundedImageView addChooseImage;
    RoundedImageView imageView;
    Images one;
    String time;
    private List<AddItem> addList = new ArrayList<>();
    private AddAdapter addAdapter;
    @SerializedName("email_address")
    public String emailAddress;
    private PoiIntent poi;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mRootView == null || mRootView.get() == null) {
            View view = inflater.inflate(R.layout.add_info, container, false);
            mRootView = new WeakReference<View>(view);
            ButterKnife.bind(this, view);
            imageView = (RoundedImageView) view.findViewById(R.id.add_chooseImage);


            ImageView StartMap = (ImageView) view.findViewById(R.id.Icon_SetPosition);
            StartMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MyApplication.getContext(), MapActivity.class);
                    if (poi != null) {
                        intent.putExtra("isSetPosition", true);
                        intent.putExtra("SetPosition", poi);

                    }
                    intent.putExtra("Address", " ");
                    startActivityForResult(intent, 1);
                }
            });
            initRefresh(view);
            initRecycle(view);
        } else {
            ViewGroup parent = (ViewGroup) mRootView.get().getParent();
            if (parent != null) {
                parent.removeView(mRootView.get());
            }
        }
        ButterKnife.bind(this, mRootView.get());
        return mRootView.get();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void initRefresh(final View view) {


        RefreshLayout refreshLayout = (RefreshLayout) view.findViewById(R.id.add_refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //onCreate(null);
                refreshlayout.finishRefresh(2000);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000);
            }
        });
    }


    public void initData() {

        addList = new ArrayList<AddItem>();
        if (mActivityDao.count()==0){
            initActivityData();
        }
for (Activity activity: mActivityDao.loadAll()){
    ClasstoItem.ActivityToAddItem(activity, addList);
}



    }

    private void initRecycle(View view) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                // Toast.makeText(MainActivity.this,sorts[pos],Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });
        recyclerView = (RecyclerView) view.findViewById(R.id.add_right_fragment_list);
        addAdapter = new AddAdapter(addList);
        recyclerView.setAdapter(addAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyApplication.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

//        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(staggeredGridLayoutManager);
//        // recyclerView.setOnItemMoveListener(mItemMoveListener);// 监听拖拽，更新UI。
       FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_add);
        fab.attachToRecyclerView(recyclerView);
        UiUtil.revealatFab(theAwesomeView, fab, addLeft);

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.select_date, R.id.activity_add_forum_send_button, R.id.get_date,  R.id.add_chooseImage})
    public void onViewClicked(View view) {

        switch (view.getId()) {

            case R.id.select_date:
                datepickerLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.activity_add_forum_send_button:

if(one!=null) {
    HttpUtil.postImage(one);
}
                StringBuilder stringBuilder = new StringBuilder("activity/"+spinner.getSelectedItem().toString()+"/");
                if (one != null) {
                    stringBuilder.append(one.getName()).toString();
                } else {
                    stringBuilder.append("picture1.jpg");
                }
                Activity activity = new Activity.Builder().title(activityAddForumTitleEdittext.getText().toString())
                        .time(time)
                        .address(activityAddForumPlaceEdittext.getText().toString())
                        .remark(activityAddForumContentEdittext.getText().toString())
                        .user_num(UserFragment.me.getId())
                        .image(stringBuilder.toString())
                        .build_data(TimeUtil.getNowTime())
                        .type(spinner.getSelectedItem().toString())
                        .add_id(UserFragment.me.getId())
                        .participatorId(UserFragment.me.getId())
                        .build();
                emailAddress=HttpUtil.getJson(activity);
                LogUtil.e(emailAddress);


HttpUtil.sendOkHttpResquest(GlobalData.HTTP_ADDRESS_ACTIVITY + "php/insertDataForActivity.php", "["+HttpUtil.getJson(activity)+"]", new Callback() {
    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {

    }
});
                Revealator.unreveal(theAwesomeView)
                        .to(fab)
                        .withCurvedTranslation()
                        .start();
                TastyToast.makeText(MyApplication.getContext(), "发送成功", TastyToast.INFO, TastyToast.SUCCESS);
                break;
            case R.id.get_date:
                int year = DatePicker.getYear();
                int month = DatePicker.getMonth() + 1;
                int day = DatePicker.getDayOfMonth();
                datepickerLayout.setVisibility(View.GONE);
                time = year + "-" + month + "-" + day;
                selectDate.setText(time);
                break;
            case R.id.add_chooseImage:
                final TwoOptionsDialog dialog = SmartisanDialog.createTwoOptionsDialog(getActivity());
                dialog.setTitle("请选择")
                        .setOp1Text("拍照")   // 设置第一个选项的文本
                        .setOp2Text("从相册中选择")   // 设置第二个选项的文本
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
            default:
                break;

        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    //这里接受返回的信息！ 可以接受SuggestionPoi对象 获取到里面的 info 获取到uid再进行解析！！,返回这边只需要   地址和店名即可！！
                    Boolean isSet = data.getBooleanExtra("isSetPosition", false);//没有返回就是false
                    StringBuilder builder = new StringBuilder();
                    if (isSet) {
                        poi = (PoiIntent) data.getSerializableExtra("SetPosition");
                        Log.d("poi11", poi.toString());
                        String address = poi.getAddress();
                        String StroeName = poi.getName();
                        builder.append("地址:" + address).append("\n\n");
                        builder.append( StroeName).append("\n");
                        //  builder.append("Lat:"+poi.getLatt()+"Long"+poi.getLongt());
                    } else {
                        builder.append("面基位置:未选择").append("\n\n");
                        builder.append("点击地图设置----->");
                    }
                    activityAddForumPlaceEdittext.setText(builder.toString());
                }
                break;
            default:
                break;
            case 2:
                switch (resultCode) {
                    case 1:
                        one = (Images) data.getSerializableExtra("Return_images");
                        setHeadview(one.getPath()); //这里去获得返回来的数据 地址！然后获得图片
                        break;
                    default:
                        break;
                }

        }
    }

    public void setHeadview(String path) {
        //在这里去设置框的装饰！！！！！！！！！

        imageView.setBackground(null);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setCornerRadius(20f);
        imageView.setBorderWidth(1f);
        imageView.setBorderColor(Color.BLUE);
        Glide.with(this).load(path).transform(new GlideRoundTransform(MyApplication.getContext(), 10)).into(imageView);

    }




}
