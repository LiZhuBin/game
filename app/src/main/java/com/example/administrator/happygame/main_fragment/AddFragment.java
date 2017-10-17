package com.example.administrator.happygame.main_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.activity.CameraFragmentMainActivity;
import com.example.administrator.happygame.activity.GetPhotoActivity;
import com.example.administrator.happygame.adapter.AddAdapter;
import com.example.administrator.happygame.adapter.ViewPagerAdapter;
import com.example.administrator.happygame.base.BaseFragment;
import com.example.administrator.happygame.been.Activity;
import com.example.administrator.happygame.my_ui.GlideRoundTransform;
import com.example.administrator.happygame.thing_class.AddItem;
import com.example.administrator.happygame.thing_class.Images;
import com.example.administrator.happygame.util.ApplicationUtil;
import com.example.administrator.happygame.util.ClasstoItem;
import com.example.administrator.happygame.util.GlobalData;
import com.example.administrator.happygame.util.HttpUtil;
import com.example.administrator.happygame.util.UiUtil;
import com.makeramen.roundedimageview.RoundedImageView;
import com.melnykov.fab.FloatingActionButton;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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


public class AddFragment extends BaseFragment {
    protected WeakReference<View> mRootView;//缓存fragment数据
    protected ViewPagerAdapter adapter;
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
    private List<AddItem> addList = new ArrayList<>();
    private AddAdapter addAdapter;
    RoundedImageView imageView;

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
            imageView= (RoundedImageView)view.findViewById(R.id.add_chooseImage);
            initRefresh(view);
            initRecycle(view);
        } else {
            ViewGroup parent = (ViewGroup) mRootView.get().getParent();
            if (parent != null) {
                parent.removeView(mRootView.get());
            }
        }
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
        HttpUtil.sendOkHttpResquest(GlobalData.httpAddressActivity + "php/userData.php", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                List<Activity> appList = HttpUtil.getListActivity(response);
                for (final Activity activity : appList) {
                    // GlobalData.httpAddressActivity+activity.getImage(),
                    ClasstoItem.ActivityToAddItem(activity, addList);

                }

            }
        });

    }

    private void initRecycle(View view) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String[] sorts = getResources().getStringArray(R.array.sorts);
                // Toast.makeText(MainActivity.this,sorts[pos],Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });
        recyclerView = (RecyclerView) view.findViewById(R.id.add_right_fragment_list);
        addAdapter = new AddAdapter(addList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ApplicationUtil.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(addAdapter);
        fab.attachToRecyclerView(recyclerView);
        UiUtil.revealatFab(theAwesomeView, fab, addLeft);

    }


    @OnClick({R.id.select_date, R.id.activity_add_forum_send_button, R.id.get_date, R.id.add_left,R.id.add_chooseImage})
    public void onViewClicked(View view) {

        switch (view.getId()) {

            case R.id.select_date:
                datepickerLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.activity_add_forum_send_button:
                break;
            case R.id.get_date:
                int year = DatePicker.getYear();
                int month = DatePicker.getMonth() + 1;
                int day = DatePicker.getDayOfMonth();
                datepickerLayout.setVisibility(View.GONE);
                selectDate.setText(year+"-"+month+"-"+day);
                // Toast.makeText(MainActivity.this, year + "-" + month + "-" + day, Toast.LENGTH_SHORT).show();
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
                        Intent intent = new Intent(ApplicationUtil.getContext(),CameraFragmentMainActivity.class);
                        startActivityForResult(intent,2);
                    }

                    @Override
                    public void onOp2() {
                        dialog.dismiss();
                        Intent intent = new Intent(ApplicationUtil.getContext(),GetPhotoActivity.class);
                        startActivityForResult(intent,2);
                    }
                });
                break;
            default:
                break;

        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode)
        {
            case 2 :
                switch (resultCode)
                {
                    case 1 :
                        Images one = (Images) data.getSerializableExtra("Return_images");
                        setHeadview(one.getPath()); //这里去获得返回来的数据 地址！然后获得图片
                        break;
                }
                break;
        }
    }
    public void setHeadview(String path)
    {
        //在这里去设置框的装饰！！！！！！！！！

        imageView.setBackground(null);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setCornerRadius(20f);
        imageView.setBorderWidth(1f);
        imageView.setBorderColor(getResources().getColor(R.color.light_gray));
        Glide.with(this).load(path).transform(new GlideRoundTransform(ApplicationUtil.getContext(),10)).into(imageView);

    }
}
