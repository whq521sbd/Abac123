package com.aotuo.vegetable.entity;

/**
 * Created by 牛XX on 2016/9/1.
 */
public class NewsListData {
    //{ ID="",Title="",Time=""}
    private String ID;
    private String Title;
    private String Time;

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getTitle() {

        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getID() {

        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
