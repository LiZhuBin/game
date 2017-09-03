package com.example.administrator.myapplication.main_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.MainActivity;
import com.example.administrator.myapplication.R;
import com.sackcentury.shinebuttonlib.ShineButton;

import butterknife.ButterKnife;


public class UserFragment extends Fragment{
    ShineButton shineButton;
    View view;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       view = inflater.inflate(R.layout.user_info, container, false);
        initData();
        return view;
    }
    @Override
    public void onDestroyView(){
        super.onDestroyView();

        ButterKnife.unbind(this);
    }
    private void initData() {
        ShineButton shineButton1 = (ShineButton) view.findViewById(R.id.shineButton);
        shineButton1.init((MainActivity) getActivity());
        shineButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }

}
