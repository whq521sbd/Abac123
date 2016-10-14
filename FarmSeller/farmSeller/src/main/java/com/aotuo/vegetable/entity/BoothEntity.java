package com.aotuo.vegetable.entity;

/**
 * Created by 牛XX on 2016/9/24.
 */

public class BoothEntity {
    //Booths=new List<BoothsA>() { UserNum="",Booth="",FullName="",Mobile1=""}
    private String UserNum;
    private String Booth;
    private String FullName;
    private String Mobile1;

    public String getUserNum() {
        return UserNum;
    }

    public void setUserNum(String userNum) {
        UserNum = userNum;
    }

    public String getBooth() {
        return Booth;
    }

    public void setBooth(String booth) {
        Booth = booth;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getMobile1() {
        return Mobile1;
    }

    public void setMobile1(String mobile1) {
        Mobile1 = mobile1;
    }
}
