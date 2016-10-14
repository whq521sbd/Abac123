package com.aotuo.vegetable.entity;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by 牛XX on 2016/8/27.
 */
public class GoodsA implements Serializable{
    //返回：new List<GoodsA>() {
    // new GoodsA() {
    // ClassXXXID="0101" ,GoodsID = "sss",
    // GoodsName = "冬瓜", Pic="asdf",
    // Price = 12.3M, Quantity=111M } }
    private String address;
    private String AreaNum;
    private String Quantity;
    private String Introduction;
    private String ClassXXXID;
    private String SellerNum;
    private String GoodsID;
    private String Price;
    private String Token;
    private String Pic;
    private String freight;
    private String AllMarket;
    private int ZT;
    private String GoodsName;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSellerNum() {
        return SellerNum;
    }

    public void setSellerNum(String sellerNum) {
        SellerNum = sellerNum;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getAreaNum() {
        return AreaNum;
    }

    public void setAreaNum(String areaNum) {
        AreaNum = areaNum;
    }

    public String getAllMarket() {
        return AllMarket;
    }

    public void setAllMarket(String allMarket) {
        AllMarket = allMarket;
    }

    public int getZT() {
        return ZT;
    }

    public void setZT(int ZT) {
        this.ZT = ZT;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getPrice() {

        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getPic() {

        return Pic;
    }

    public void setPic(String pic) {
        Pic = pic;
    }

    public String getGoodsName() {

        return GoodsName;
    }

    public void setGoodsName(String goodsName) {
        GoodsName = goodsName;
    }

    public String getGoodsID() {

        return GoodsID;
    }

    public void setGoodsID(String goodsID) {
        GoodsID = goodsID;
    }

    public String getClassXXXID() {

        return ClassXXXID;
    }

    public void setClassXXXID(String classXXXID) {
        ClassXXXID = classXXXID;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getIntroduction() {

        return Introduction;
    }

    public void setIntroduction(String introduction) {
        Introduction = introduction;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
