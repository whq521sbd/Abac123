package com.aotuo.vegetable.entity;

/**
 * Created by 牛XX on 2016/9/3.
 */
public class ContactEntity {
    /**
     * {
     "UserType": "1",
     "Booth": "00001",
     "MarketName": "北京新发地蔬菜集团",
     "RecName": "北京老李",
     "RecID": "110101000001",
     "LastTime": "2016-09-30 14:48:50"
     },
     */
    private String HeadImg;
    private String UserType;
    private String Booth;
    private String MarketName;
    private String RecID;
    private String RecName;
    private String ComName;
    private String LastTime;

    public String getHeadImg() {
        return HeadImg;
    }

    public void setHeadImg(String headImg) {
        HeadImg = headImg;
    }

    public String getUserType() {
        return UserType;
    }

    public String getComName() {
        return ComName;
    }

    public void setComName(String comName) {
        ComName = comName;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }

    public String getBooth() {
        return Booth;
    }

    public void setBooth(String booth) {
        Booth = booth;
    }

    public String getMarketName() {
        return MarketName;
    }

    public void setMarketName(String marketName) {
        MarketName = marketName;
    }

    public String getRecID() {
        return RecID;
    }

    public void setRecID(String recID) {
        RecID = recID;
    }

    public String getRecName() {
        return RecName;
    }

    public void setRecName(String recName) {
        RecName = recName;
    }

    public String getLastTime() {
        return LastTime;
    }

    public void setLastTime(String lastTime) {
        LastTime = lastTime;
    }
}
