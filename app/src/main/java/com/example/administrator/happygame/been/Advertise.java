package com.example.administrator.happygame.been;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/10/1 0001.
 */

public class Advertise  implements Parcelable {
    public static final Parcelable.Creator<Advertise> CREATOR = new Parcelable.Creator<Advertise>() {
        public Advertise createFromParcel(Parcel source) {
            return new Advertise(source);
        }

        public Advertise[] newArray(int size) {
            return new Advertise[size];
        }
    };
    /**
     * advertise_id : 1
     * advertise_image : advertise/4703fbe8dcfc601964ad8f92b61d98a6.png
     */

    private String advertise_id;
    private String advertise_image;

    public Advertise(String advertise_image) {
        this.advertise_image = advertise_image;
    }

    protected Advertise(Parcel in) {
        this.advertise_id = in.readString();
        this.advertise_image = in.readString();
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
}
