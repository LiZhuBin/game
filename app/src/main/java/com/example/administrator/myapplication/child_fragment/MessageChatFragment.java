package com.example.administrator.myapplication.child_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;


public class MessageChatFragment extends Fragment {
View view;
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
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_message_chat, container, false);
        return view;
    }

    public static MessageChatFragment getInstance() {
        MessageChatFragment sf = new MessageChatFragment();
        return sf;
    }
}
