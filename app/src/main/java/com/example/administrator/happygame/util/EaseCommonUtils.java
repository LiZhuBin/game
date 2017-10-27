package com.example.administrator.happygame.util;

import com.hyphenate.chat.EMConversation;

/**
 * 作者：Administrator on 2017/10/26 0026 10:47
 * 邮箱：xjs250@163.com
 */

public class EaseCommonUtils {
    public static final int CHATTYPE_SINGLE = 1;
    public static final int CHATTYPE_GROUP = 2;
    public static EMConversation.EMConversationType getConversationType(int chatType) {
        if (chatType ==CHATTYPE_SINGLE) {
            return EMConversation.EMConversationType.Chat;
        } else if (chatType ==CHATTYPE_GROUP) {
            return EMConversation.EMConversationType.GroupChat;
        } else {
            return EMConversation.EMConversationType.ChatRoom;
        }
    }
}
