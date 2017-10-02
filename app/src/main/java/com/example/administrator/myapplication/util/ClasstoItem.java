package com.example.administrator.myapplication.util;

import com.example.administrator.myapplication.been.Activity;
import com.example.administrator.myapplication.thing_class.AddItem;

import java.util.List;

/**
 * Created by Administrator on 2017/10/2 0002.
 */

public class ClasstoItem {

    public static void  ActivitytoAddItem(Activity activity, List<AddItem> addItemList){
        addItemList.add(new AddItem(activity.getId(),
                activity.getImage(),
                activity.getUser_num(),
                activity.getTitle(),
                activity.getAddress(),
                activity.getTime(),
                activity.getAddress()));
    }
}
