package com.example.administrator.myapplication.child_fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.util.ApplicationUtil;


public class LeftFragment extends Fragment {

    int[] logos=new int[]{
            R.drawable.icon_game_card,
            R.drawable.icon_game_chess,
            R.drawable.icon_game_athletics,
            R.drawable.icon_game_others
    };
    private String[] armsTypes=new String[]
            {"卡牌","棋类","竞技","其他"};
    private String[][] arms=new String[][]{
            {"三国杀","狼人杀","游戏王"},
            {"三国杀","狼人杀","游戏王"},
            {"三国杀","狼人杀","游戏王"},
            {"三国杀","狼人杀","游戏王"}
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_left_fragment, container, false);
        ExpandableListAdapter adapter=new BaseExpandableListAdapter() {

            @Override
            public int getGroupCount() {
                return armsTypes.length;
            }

            @Override
            public int getChildrenCount(int i) {
                return arms[i].length;
            }

            @Override
            public Object getGroup(int i) {
                return armsTypes[i];
            }

            @Override
            public Object getChild(int i, int i1) {
                return arms[i][i1];
            }

            @Override
            public long getGroupId(int i) {
                return i;
            }

            @Override
            public long getChildId(int i, int i1) {
                return i1;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            @Override
            public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
                LinearLayout l1=new LinearLayout(ApplicationUtil.getContext());
                l1.setBackgroundColor(Color.GRAY);
                l1.setOrientation(LinearLayout.VERTICAL);
                ImageView logo=new ImageView(ApplicationUtil.getContext());
                Glide.with(ApplicationUtil.getContext()).load(logos[i]).override(120,120).into(logo);
                l1.addView(logo);
                TextView textView=getTextView(30);
                textView.setText(getGroup(i).toString());
                l1.addView(textView);
                return l1;
            }

            @Override
            public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
                TextView textView=getTextView(20);

                textView.setText(getChild(i,i1).toString());
                return textView;

            }

            @Override
            public boolean isChildSelectable(int i, int i1) {
                return true;
            }
            private TextView  getTextView(int textSize){
                AbsListView.LayoutParams lp=new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,120);
                TextView textView=new TextView(ApplicationUtil.getContext());
                textView.setLayoutParams(lp);
                textView.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
                textView.setPadding(30,0,0,0);
                textView.setTextColor(Color.BLACK);
                textView.setTextSize(textSize);
                return textView;
            }
        };
        final ExpandableListView expandableListView=(ExpandableListView)view.findViewById(R.id.left_expandable_list);
        expandableListView.setGroupIndicator(null);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(ApplicationUtil.getContext(), arms[groupPosition][childPosition]+"", Toast.LENGTH_SHORT).show();
                return true;

            }
        });

        expandableListView.setAdapter(adapter);
        // Inflate the layout for this fragment

        return view;
    }


}
