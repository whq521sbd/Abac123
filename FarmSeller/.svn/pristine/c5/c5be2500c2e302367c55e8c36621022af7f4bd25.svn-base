package com.aotuo.vegetable.entity;

/**
 * Created by 牛XX on 2016/8/26.
 */
public class LoginResultEntity {
    //返回： { Token = "tokenValue",
    // MarketNum="",MarketName="",AreaVer='20160826151730',
    //                         CheckTPWD=true,isSeller=true,FunMenu=",11,",ClassVer="20160826151730" }

    // { Token = "tokenValue",CheckTPWD=true,isSeller=true,FunMenu=",11,",ClassVer="20160826151730" }
    private String Token;
    private String MarketNum;
    private int CheckSMS;
    private float SMSLimit;
    private String MarketName;
    private boolean CheckTPWD;
    private boolean isSeller;
    private String FunMenu;
    private String ClassVer;
    private String AreaVer;

    public int getCheckSMS() {
        return CheckSMS;
    }

    public void setCheckSMS(int checkSMS) {
        CheckSMS = checkSMS;
    }

    public float getSMSLimit() {
        return SMSLimit;
    }

    public void setSMSLimit(float SMSLimit) {
        this.SMSLimit = SMSLimit;
    }

    /**
     * //卖方
     摊位卖本地单位用户=11,(默认)
     摊位看外地市场 = 12,
     摊位卖外地市场 =13,
     摊位发布到全网=14,
     //买方
     单位食堂买本地=21,(默认)
     摊位买外地市场=22
     * @return
     */
    public String getFunMenu() {
        return FunMenu;
    }

    public void setFunMenu(String funMenu) {
        FunMenu = funMenu;
    }

    public boolean isSeller() {

        return isSeller;
    }

    public String getMarketName() {
        return MarketName;
    }

    public void setMarketName(String marketName) {
        MarketName = marketName;
    }

    public String getMarketNum() {

        return MarketNum;
    }

    public void setMarketNum(String marketNum) {
        MarketNum = marketNum;
    }

    public void setSeller(boolean seller) {
        isSeller = seller;
    }

    public boolean isCheckTPWD() {
        return CheckTPWD;
    }

    public void setCheckTPWD(boolean checkTPWD) {
        CheckTPWD = checkTPWD;
    }

    public String getClassVer() {
        return ClassVer;
    }

    public void setClassVer(String classVer) {
        ClassVer = classVer;
    }

    public String getAreaVer() {
        return AreaVer;
    }

    public void setAreaVer(String areaVer) {
        AreaVer = areaVer;
    }

    public String getToken() {

        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
