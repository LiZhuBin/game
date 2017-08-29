package com.example.administrator.myapplication.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.FruitAdapter;
import com.example.administrator.myapplication.thing_class.Fruit;
import com.example.administrator.myapplication.util.ApplicationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BaseChildFragment extends Fragment {
    private List<Fruit> fruitList = new ArrayList<>();
    private FruitAdapter adapter;
    private RecyclerView recyclerView;
    public static Fruit[] fruits = {
            new Fruit("1", R.drawable.image_scrolling_head),
            new Fruit("2", R.drawable.image_scrolling_head),
            new Fruit("3", R.drawable.image_scrolling_head),
            new Fruit("4", R.drawable.image_scrolling_head),
            new Fruit("5", R.drawable.image_scrolling_head)
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_child, container, false);
        initFruits();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(ApplicationUtil.getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);

        return view;
    }
    private void initFruits() {
        fruitList.clear();
        for (int i = 0; i < 40; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }

}
