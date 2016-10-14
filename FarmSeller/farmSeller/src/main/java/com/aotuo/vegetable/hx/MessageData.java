package com.aotuo.vegetable.hx;

/**
 * Created by 牛XX on 2016/9/2.
 */
public class MessageData {
    //{ID="",SendID="",RecID="",Content="",Time="",SendName=""}
    private String ID;
    private String SendID;
    private String RecID;
    private String Content;
    private String Time;
    private String SendName;
    private String isSend;

    public String getIsSend() {
        return isSend;
    }

    public void setIsSend(String isSend) {
        this.isSend = isSend;
    }

    public String getSendName() {
        return SendName;
    }

    public void setSendName(String sendName) {
        SendName = sendName;
    }

    public String getTime() {

        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getContent() {

        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getRecID() {

        return RecID;
    }

    public void setRecID(String recID) {
        RecID = recID;
    }

    public String getSendID() {

        return SendID;
    }

    public void setSendID(String sendID) {
        SendID = sendID;
    }

    public String getID() {

        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
