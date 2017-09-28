package com.example.administrator.myapplication.been;


import java.io.Serializable;
import java.net.URL;
import java.sql.Time;

/**
 * Created by lenovo on 2017/9/6.
 */

public class News implements Serializable {

    public enum  NewType{

        LangRenSha(0),SanGuoSha(1),YouXiWang(2);

        private int mCode ;

        private NewType(int Code)
        {
            mCode = Code ;
        }


        static private int getListSize(){ return 3; }


        @Override
        public String toString() {
            return super.toString();
        }
    }





    private String  image_title;
    private String  bottom_title ;
    private int    image_Res_id ;
    private URL     image_Uri_adress;
    private NewType type ;
    private String  Creator;
    private long    ReadTime;
    private Time    StartTime;

    public String getCreator() {
        return Creator;
    }

    public void setCreator(String creator) {
        Creator = creator;
    }

    public long getReadTime() {
        return ReadTime;
    }

    public void setReadTime(long readTime) {
        ReadTime = readTime;
    }

    public Time getStartTime() {
        return StartTime;
    }

    public void setStartTime(Time startTime) {
        StartTime = startTime;
    }

    public NewType getType() {
        return type;
    }

    public void setType(NewType type) {
        this.type = type;
    }

    private boolean isUse ;

    public boolean isUse() {
        return isUse;
    }

    public void setUse(boolean use) {
        isUse = use;
    }

    public News (NewType newType,int image_Res_id , String image_title , String bottom_title )
    {
        this(image_Res_id ,image_title ,bottom_title );
        type = newType ;
    }

    public News(int image_Res_id , String image_title , String bottom_title ){

        this.image_Res_id = image_Res_id;
        this.image_title  = image_title;
        this.bottom_title = bottom_title;


    }
    public News(URL url , String image_title , String bottom_title ){

        this.image_Uri_adress = url ;
        this.image_title  = image_title;
        this.bottom_title = bottom_title;


    }


    public String getImage_title() {
        return image_title;
    }

    public void setImage_title(String image_title) {
        this.image_title = image_title;
    }

    public String getBottom_title() {
        return bottom_title;
    }

    public void setBottom_title(String bottom_title) {
        this.bottom_title = bottom_title;
    }

    public int getImage_Res_id() {
        return image_Res_id;
    }

    public void setImage_Res_id(int image_Res_id) {
        this.image_Res_id = image_Res_id;
    }

    public URL getImage_Uri_adress() {
        return image_Uri_adress;
    }

    public void setImage_Uri_adress(URL image_Uri_adress) {
        this.image_Uri_adress = image_Uri_adress;
    }
}
