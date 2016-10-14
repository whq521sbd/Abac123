package com.aotuo.vegetable.entity;

import java.util.List;

/**
 * Created by 牛XX on 2016/9/20.
 */

public class GoodsMessage {
    private int CurPage;
    private int PageSize;
    private List<GoodsA> Goods;

    public int getCurPage() {
        return CurPage;
    }

    public void setCurPage(int curPage) {
        CurPage = curPage;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    public List<GoodsA> getGoods() {
        return Goods;
    }

    public void setGoods(List<GoodsA> goods) {
        Goods = goods;
    }
}
