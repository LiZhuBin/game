package com.example.administrator.myapplication.main_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.allen.library.SuperButton;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.ViewPagerAdapter;
import com.ramotion.foldingcell.FoldingCell;

import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


public class AddFragment extends Fragment {

    SuperButton add;
    protected ViewPagerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_info, container, false);
        ButterKnife.bind(this, view);
        adapter = new ViewPagerAdapter(getChildFragmentManager());

        final FoldingCell fc = (FoldingCell)view.findViewById(R.id.folding_cell);
        fc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fc.toggle(false);
            }
        });
        final LinearLayout layout2=view.findViewById(R.id.new_user_linear_layout);
        layout2.setOrientation(LinearLayout.HORIZONTAL);
        add=(SuperButton)view.findViewById(R.id.new_content_add_button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CircleImageView myImageView=new CircleImageView(view.getContext());
                myImageView.setImageResource(R.drawable.image_whilte_me);
                layout2.addView(myImageView);

            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
