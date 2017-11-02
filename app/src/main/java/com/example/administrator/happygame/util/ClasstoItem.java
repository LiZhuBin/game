package com.example.administrator.happygame.util;

import com.example.administrator.happygame.been.Activity;
import com.example.administrator.happygame.been.Advertise;
import com.example.administrator.happygame.been.Forum;
import com.example.administrator.happygame.been.News;
import com.example.administrator.happygame.been.User;
import com.example.administrator.happygame.my_ui.Headview;
import com.example.administrator.happygame.thing_class.AddItem;
import com.example.administrator.happygame.thing_class.Advertisement;
import com.example.administrator.happygame.thing_class.ForumItem;

import java.util.List;

/**
 * Created by Administrator on 2017/10/2 0002.
 */

public class ClasstoItem {

    public static void ActivityToAddItem(Activity activity, List<AddItem> addItemList) {
        addItemList.add(new AddItem(activity.getId(),
                activity.getImage(),
                activity.getUser_num(),
                activity.getTitle(),
                activity.getRemark(),
                activity.getTime(),
                activity.getAddress()));
    }

    public static void ForumToForumItem(Forum forum, List<ForumItem> forumItemList) {
        ForumItem forumItem = new ForumItem(forum.getTitle(), StringUtil.httpArrayStringLength(forum.getComment()), forum.getLike(), forum.getImage(), forum.getId());
        forumItemList.add(forumItem);
    }

    public static void UserToHeadview(User user, List<Headview> headviewList) {
        Headview headview = new Headview(user.getId(), user.getImage(), user.getName());
        headviewList.add(headview);
    }

    public static void NewToChooseNews(News news, List<News> chooseNews) {
        chooseNews.add(news);
    }

    public static void AdvertiseToAdvertiseItem(Advertise advertise, List<Advertisement> advertiseList) {
        advertiseList.add(new Advertisement(GlobalData.HTTP_ADDRESS_PICTURE + advertise.getAdvertise_image()));
    }
}
