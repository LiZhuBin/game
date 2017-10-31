package com.example.administrator.happygame.been;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Administrator on 2017/10/1 0001.
 */
@Entity
public class Advertise implements Parcelable {
    public static final Creator<Advertise> CREATOR = new Creator<Advertise>() {
        @Override
        public Advertise createFromParcel(Parcel source) {
            return new Advertise(source);
        }

        @Override
        public Advertise[] newArray(int size) {
            return new Advertise[size];
        }
    };
    /**
     * advertise_id : 1
     * advertise_image : advertise/4703fbe8dcfc601964ad8f92b61d98a6.png
     */
    @Id
    private String advertise_id;
    private String advertise_image;

    public Advertise(String advertise_image) {
        this.advertise_image = advertise_image;
    }

    protected Advertise(Parcel in) {
        this.advertise_id = in.readString();
        this.advertise_image = in.readString();
    }

    @Generated(hash = 1176378120)
    public Advertise(String advertise_id, String advertise_image) {
        this.advertise_id = advertise_id;
        this.advertise_image = advertise_image;
    }

    @Generated(hash = 1516411721)
    public Advertise() {
    }

    private Advertise(Builder builder) {
        setAdvertise_id(builder.advertise_id);
        setAdvertise_image(builder.advertise_image);
    }

    public String getAdvertise_id() {
        return advertise_id;
    }

    public void setAdvertise_id(String advertise_id) {
        this.advertise_id = advertise_id;
    }

    public String getAdvertise_image() {
        return advertise_image;
    }

    public void setAdvertise_image(String advertise_image) {
        this.advertise_image = advertise_image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.advertise_id);
        dest.writeString(this.advertise_image);
    }

    public static final class Builder {
        private String advertise_id;
        private String advertise_image;

        public Builder() {
        }

        public Builder advertise_id(String val) {
            advertise_id = val;
            return this;
        }

        public Builder advertise_image(String val) {
            advertise_image = val;
            return this;
        }

        public Advertise build() {
            return new Advertise(this);
        }
    }
}
