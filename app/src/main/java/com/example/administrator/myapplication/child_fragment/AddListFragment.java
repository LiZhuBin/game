package com.example.administrator.myapplication.child_fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.been.Activity;
import com.example.administrator.myapplication.util.IntentHelp;
import com.example.administrator.myapplication.util.StringUtil;

import butterknife.Bind;
import butterknife.ButterKnife;


public class AddListFragment extends Fragment {

    public static Activity thisActivity;
    ImageView activityAddImage;
    String obj;
    @Bind(R.id.add_content_title)
    SuperTextView addContentTitle;
    @Bind(R.id.output_autofit)
    TextView outputAutofit;
    @Bind(R.id.add_content_time)
    SuperTextView addContentTime;
    @Bind(R.id.add_content_address)
    SuperTextView addContentAddress;

    SuperTextView addContentPerson;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_list, container, false);
        initClick(view,thisActivity);
   new MyAsyncTask(thisActivity).execute();

        ButterKnife.bind(this, view);
        return view;
    }
    public void initClick(View view,Activity activity){
        addContentPerson=(SuperTextView)view.findViewById(R.id.add_content_person);
        addContentPerson.setOnClickListener(new SuperTextView.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(IntentHelp.toFriendsActivity(thisActivity.getAdd_id(),"已加入的人"));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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

            addContentTitle.setCenterString(activity.getTitle());
            outputAutofit.setText(activity.getRemark());
            addContentTime.setCenterString(activity.getTitle());
            addContentAddress.setCenterString(activity.getAddress());
            addContentPerson.setRightString(StringUtil.httpArrayStringLength(activity.getAdd_id()));
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            publishProgress(1);
            return true;
        }
    }

    public static AddListFragment getInstance(String title) {
        AddListFragment sf = new AddListFragment();
        return sf;
    }
}
