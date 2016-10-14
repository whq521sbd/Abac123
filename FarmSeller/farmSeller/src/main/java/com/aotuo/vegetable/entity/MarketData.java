package com.aotuo.vegetable.entity;

import java.util.List;

/**
 * Created by 牛XX on 2016/9/24.
 */

public class MarketData {
    //返回：PageSize = PageSize, CurPage = curPage ,Markets=new List<MarketA>() { Num="",Name="",Addr=""}
    private int PageSize;
    private int CurPage;
    private List<MarketEntity> Markets;

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    public int getCurPage() {
        return CurPage;
    }

    public void setCurPage(int curPage) {
        CurPage = curPage;
    }

    public List<MarketEntity> getMarkets() {
        return Markets;
    }

    public void setMarkets(List<MarketEntity> markets) {
        Markets = markets;
    }
}
