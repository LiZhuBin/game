package com.example.administrator.happygame.been;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者：Administrator on 2017/11/2 0002 21:22
 * 邮箱：xjs250@163.com
 */
@Entity
public class Chat implements Parcelable {

    @Id
    private  String userId;
    private String userName;
    private String userImageUrl;
    private String lastMessage;
    private String getMsgTime;
private int msgNoRead;
@Generated(hash = 1524264650)
public Chat(String userId, String userName, String userImageUrl,
        String lastMessage, String getMsgTime, int msgNoRead) {
    this.userId = userId;
    this.userName = userName;
    this.userImageUrl = userImageUrl;
    this.lastMessage = lastMessage;
    this.getMsgTime = getMsgTime;
    this.msgNoRead = msgNoRead;
}
@Generated(hash = 519536279)
public Chat() {
}

    private Chat(Builder builder) {
        setUserId(builder.userId);
        setUserName(builder.userName);
        setUserImageUrl(builder.userImageUrl);
        setLastMessage(builder.lastMessage);
        setGetMsgTime(builder.getMsgTime);
        setMsgNoRead(builder.msgNoRead);
    }

    public String getUserId() {
    return this.userId;
}
public void setUserId(String userId) {
    this.userId = userId;
}
public String getUserName() {
    return this.userName;
}
public void setUserName(String userName) {
    this.userName = userName;
}
public String getUserImageUrl() {
    return this.userImageUrl;
}
public void setUserImageUrl(String userImageUrl) {
    this.userImageUrl = userImageUrl;
}
public String getLastMessage() {
    return this.lastMessage;
}
public void setLastMessage(String lastMessage) {
    this.lastMessage = lastMessage;
}
public String getGetMsgTime() {
    return this.getMsgTime;
}
public void setGetMsgTime(String getMsgTime) {
    this.getMsgTime = getMsgTime;
}
public int getMsgNoRead() {
    return this.msgNoRead;
}
public void setMsgNoRead(int msgNoRead) {
    this.msgNoRead = msgNoRead;
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.userName);
        dest.writeString(this.userImageUrl);
        dest.writeString(this.lastMessage);
        dest.writeString(this.getMsgTime);
        dest.writeInt(this.msgNoRead);
    }

    protected Chat(Parcel in) {
        this.userId = in.readString();
        this.userName = in.readString();
        this.userImageUrl = in.readString();
        this.lastMessage = in.readString();
        this.getMsgTime = in.readString();
        this.msgNoRead = in.readInt();
    }

    public static final Creator<Chat> CREATOR = new Creator<Chat>() {
        @Override
        public Chat createFromParcel(Parcel source) {
            return new Chat(source);
        }

        @Override
        public Chat[] newArray(int size) {
            return new Chat[size];
        }
    };

    public static final class Builder {
        private String userId;
        private String userName;
        private String userImageUrl;
        private String lastMessage;
        private String getMsgTime;
        private int msgNoRead;

        public Builder() {
        }

        public Builder userId(String val) {
            userId = val;
            return this;
        }

        public Builder userName(String val) {
            userName = val;
            return this;
        }

        public Builder userImageUrl(String val) {
            userImageUrl = val;
            return this;
        }

        public Builder lastMessage(String val) {
            lastMessage = val;
            return this;
        }

        public Builder getMsgTime(String val) {
            getMsgTime = val;
            return this;
        }

        public Builder msgNoRead(int val) {
            msgNoRead = val;
            return this;
        }

        public Chat build() {
            return new Chat(this);
        }
    }
}
