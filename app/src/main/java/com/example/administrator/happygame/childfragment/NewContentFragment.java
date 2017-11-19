package com.example.administrator.happygame.childfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.happygame.R;
import com.example.administrator.happygame.base.BaseFragment;
import com.example.administrator.happygame.been.News;
import com.example.administrator.happygame.util.TimeUtil;

import java.text.ParseException;

import butterknife.Bind;
import butterknife.ButterKnife;


public class NewContentFragment extends BaseFragment {
    View view;
    @Bind(R.id.news_title)
    TextView newsTitle;
    @Bind(R.id.news_time)
    TextView newsTime;
    @Bind(R.id.news_content)
    TextView newsContent;

    private News one;

    public static NewContentFragment getInstance() {
        NewContentFragment sf = new NewContentFragment();
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            return view;
        }
        View view = inflater.inflate(R.layout.fragment_new_list, container, false);
        // Inflate the layout for this fragment
        ButterKnife.bind(this, view);
        getNews();
        try {
            initLayoutview(view);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return view;

    }

    private void initLayoutview(View view) throws ParseException {


        // TextView  textview_title = (TextView)findViewById(R.id.NewAcitivity_titletext);


        String Main_Context = one.getNew_content();
        String Title_Context = one.getNew_title();

        newsTitle.setText(Title_Context);
        newsContent.setText("  "+Main_Context);
        newsTime.setText(TimeUtil.getTimeFormatText(one.getNew_build_time()));

    }

    public void getNews() {
        Intent intent = getActivity().getIntent();
        News obj = (News) intent.getParcelableExtra("Object_news");
        one = obj;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
