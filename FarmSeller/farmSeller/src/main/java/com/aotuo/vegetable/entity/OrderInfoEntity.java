package com.aotuo.vegetable.entity;

import java.io.Serializable;

/**
 * Created by 牛XX on 2016/9/5.
 */
public class OrderInfoEntity implements Serializable{
    //{ Num,Buyer,BuyerNum,SellerSellerNum,Sum,GoodsTitle,Price,Weight,Time);
    private String Num;
    private String Buyer;
    private String BuyerNum;
    private String SellerSellerNum;
    private String Sum;
    private String GoodsTitle;
    private String Price;
    private String Weight;
    private String Time;

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getWeight() {

        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getPrice() {

        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getGoodsTitle() {

        return GoodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        GoodsTitle = goodsTitle;
    }

    public String getSum() {

        return Sum;
    }

    public void setSum(String sum) {
        Sum = sum;
    }

    public String getSellerSellerNum() {

        return SellerSellerNum;
    }

    public void setSellerSellerNum(String sellerSellerNum) {
        SellerSellerNum = sellerSellerNum;
    }

    public String getBuyerNum() {

        return BuyerNum;
    }

    public void setBuyerNum(String buyerNum) {
        BuyerNum = buyerNum;
    }

    public String getBuyer() {

        return Buyer;
    }

    public void setBuyer(String buyer) {
        Buyer = buyer;
    }

    public String getNum() {

        return Num;
    }

    public void setNum(String num) {
        Num = num;
    }
}
