package com.example.administrator.myapplication.child_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.been.News;


public class NewListFragment extends Fragment {
    private News one ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_new_list, container, false);
        // Inflate the layout for this fragment
        getNews();
        initLayoutview(view);
        return view;

    }
    private void initLayoutview(View view) {


        // TextView  textview_title = (TextView)findViewById(R.id.NewAcitivity_titletext);
        TextView textview_main_content = (TextView)view.findViewById(R.id.NewAcitivity_Title_mainText);

        String Main_Context = one.getNew_content();
        String Title_Context = one.getNew_title();

        //       textview_title.setText(Title_Context);
        textview_main_content.setText(Main_Context);


    }


    public void getNews() {
        Intent intent = getActivity().getIntent();
        News obj = (News) intent.getSerializableExtra("Object_news");
        one = obj ;
    }
    public static NewListFragment getInstance(String title) {
        NewListFragment sf = new NewListFragment();
        return sf;
    }
}
