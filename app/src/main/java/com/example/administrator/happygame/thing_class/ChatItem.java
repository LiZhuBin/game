package com.example.administrator.happygame.thing_class;

/**
 * 作者：Administrator on 2017/11/1 0001 12:12
 * 邮箱：xjs250@163.com
 */

public class ChatItem {
    private String imageUrl;
    private  String chatName;
    private  String chatContent;
    private String BuildId;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public String getChatContent() {
        return chatContent;
    }

    public void setChatContent(String chatContent) {
        this.chatContent = chatContent;
    }

    public String getBuildId() {
        return BuildId;
    }

    public void setBuildId(String buildId) {
        BuildId = buildId;
    }

    public ChatItem(String imageUrl, String chatName, String chatContent, String buildId) {
        this.imageUrl = imageUrl;
        this.chatName = chatName;
        this.chatContent = chatContent;
        BuildId = buildId;
    }
}
