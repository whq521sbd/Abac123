package com.aotuo.vegetable.entity;

public class RecordEntity {
    /**
     *
     "Buyer": "王五",
     "Num": "20160927132427936ZP4",
     "SellerNum": "110101000001",
     "LogMoney": "0.00",
     "GoodsMoney": "12.00",
     "GoodsTitle": "wfw",
     "Seller": "北京老李",
     "LogRate": "0.00",
     "Sum": "12.00",
     "Time": "2016-09-27 13:51:46",
     "State": "完成",
     "PicPath": "\/UImage\/Goods\/201609\/20160927010841H2V.JPG",
     "Weight": "12.00",
     "LogDis": "0.00",
     "Price": "1.00",
     "Market": "北京新发地蔬菜集团",
     "Booth": "00001",
     "BuyerNum": "370101000003"
     */
    private String Sum;
    private String Buyer;
    private String SellerNum;
    private String LogMoney;
    private String GoodsMoney;
    private String GoodsTitle;
    private String Seller;
    private String LogRate;
    private String Time;
    private String State;
    private String PicPath;
    private String LogDis;
    private String Weight;
    private String Price;
    private String Market;
    private String Num;
    private String Booth;
    private String BuyerNum;

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getMarket() {
        return Market;
    }

    public void setMarket(String market) {
        Market = market;
    }

    public String getBooth() {
        return Booth;
    }

    public void setBooth(String booth) {
        Booth = booth;
    }

    public String getLogMoney() {
        return LogMoney;
    }

    public void setLogMoney(String logMoney) {
        LogMoney = logMoney;
    }

    public String getGoodsMoney() {
        return GoodsMoney;
    }

    public void setGoodsMoney(String goodsMoney) {
        GoodsMoney = goodsMoney;
    }

    public String getLogRate() {
        return LogRate;
    }

    public void setLogRate(String logRate) {
        LogRate = logRate;
    }

    public String getLogDis() {
        return LogDis;
    }

    public void setLogDis(String logDis) {
        LogDis = logDis;
    }

    public String getPicPath() {
        return PicPath;
    }

    public void setPicPath(String picPath) {
        PicPath = picPath;
    }

    public String getBuyerNum() {
        return BuyerNum;
    }

    public void setBuyerNum(String buyerNum) {
        BuyerNum = buyerNum;
    }

    public String getSeller() {

        return Seller;
    }

    public void setSeller(String seller) {
        Seller = seller;
    }

    public String getGoodsTitle() {

        return GoodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        GoodsTitle = goodsTitle;
    }

    public String getPrice() {

        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getSellerNum() {

        return SellerNum;
    }

    public void setSellerNum(String sellerNum) {
        SellerNum = sellerNum;
    }

    public String getNum() {

        return Num;
    }

    public void setNum(String num) {
        Num = num;
    }

    public String getBuyer() {

        return Buyer;
    }

    public void setBuyer(String buyer) {
        Buyer = buyer;
    }

    public String getWeight() {

        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getTime() {

        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getSum() {

        return Sum;
    }

    public void setSum(String sum) {
        Sum = sum;
    }
}
