package com.example.administrator.happygame.child_fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.bumptech.glide.Glide;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.base.BaseFragment;
import com.example.administrator.happygame.been.Activity;
import com.example.administrator.happygame.been.User;
import com.example.administrator.happygame.util.GlobalData;
import com.example.administrator.happygame.util.IntentHelp;
import com.example.administrator.happygame.util.MyApplication;
import com.example.administrator.happygame.util.StringUtil;
import com.example.administrator.happygame.util.TimeUtil;
import com.jaouan.revealator.Revealator;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.example.administrator.happygame.util.GlobalData.mUserDao;


public class AddContentFragment extends BaseFragment {


    @Bind(R.id.add_content_title)
    SuperTextView addContentTitle;
    @Bind(R.id.output_autofit)
    TextView outputAutofit;
    @Bind(R.id.add_content_time)
    SuperTextView addContentTime;
    @Bind(R.id.add_content_address)
    SuperTextView addContentAddress;
    @Bind(R.id.add_content_build_time)
    SuperTextView addContentBuildTime;
    @Bind(R.id.add_content_num)
    SuperTextView addContentNum;
    @Bind(R.id.item_edit)
    EditText itemEdit;
    @Bind(R.id.edit_ensure)
    Button editEnsure;
    @Bind(R.id.edit_frame)
    FrameLayout editFrame;
    private Activity activity;

    View view;
    @Bind(R.id.add_my_image)
    ImageView addMyImage;
    @Bind(R.id.add_my_name)
    TextView addMyName;
    @Bind(R.id.add_my_add)
    Button addMyAdd;
    @Bind(R.id.add_content_person)
    SuperTextView addContentPerson;

    public static AddContentFragment getInstance() {
        AddContentFragment sf = new AddContentFragment();
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            return view;
        }
        view = inflater.inflate(R.layout.fragment_add_list, container, false);


        ButterKnife.bind(this, view);
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEventBackgroundThread(Activity activity) {
        this.activity = activity;
        initClick(view, activity);
        new MyAsyncTask(activity).execute();

    }

    public void initClick(View view, final Activity activity) {
        addContentPerson = (SuperTextView) view.findViewById(R.id.add_content_person);
        addContentPerson.setOnClickListener(new SuperTextView.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(IntentHelp.toFriendsActivity(activity.getAdd_id(), "已加入的人"));
            }
        });
        addContentAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(IntentHelp.toMapActivity(activity.getAddress()));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.add_my_image, R.id.add_my_add,R.id.edit_ensure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_my_image:
                startActivity(IntentHelp.toPersonActivity(activity.getParticipatorId(), 0));
                break;
            case R.id.add_my_add:
                Revealator.reveal(editFrame)
                        .from(addMyAdd)
                        .withCurvedTranslation()
                        .withChildsAnimation()
                        .start();
             break;
            case R.id.edit_ensure:
                Revealator.unreveal(editFrame)
                        .to(addMyAdd)
                        .withCurvedTranslation()
                        .start();
                new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("您的好友请求已发送")
                        .setContentText("请等待对方同意")
                        .setConfirmText(" 好的 ")
                        .showCancelButton(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.cancel();
                            }
                        })
                        .show();
                break;
            default:
                break;
        }
    }



    class MyAsyncTask extends AsyncTask<Void, Integer, Boolean> {
        Activity activity;

        public MyAsyncTask(Activity activity) {
            super();
            this.activity = activity;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {


        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            User user = mUserDao.load(activity.getParticipatorId());
            addMyName.setText(user.getName());
            Glide.with(MyApplication.getContext()).load(GlobalData.HTTP_ADDRESS_PICTURE + user.getImage()).into(addMyImage);
            addContentNum.setRightString(activity.getUser_num());
            try {
                addContentBuildTime.setRightString(TimeUtil.getTimeFormatText(activity.getBuild_data()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            addContentTitle.setCenterString(activity.getTitle());
            outputAutofit.setText(activity.getRemark());
            addContentTime.setCenterString(activity.getTime());
            addContentAddress.setCenterString(activity.getAddress());
            addContentPerson.setRightString(StringUtil.httpArrayStringLength(activity.getAdd_id()));
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            publishProgress(1);
            return true;
        }
    }
}
