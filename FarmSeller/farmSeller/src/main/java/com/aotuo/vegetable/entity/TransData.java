package com.aotuo.vegetable.entity;

import java.util.List;

/**
 * Created by 牛XX on 2016/10/10.
 */

public class TransData {
    //返回： PageSize = PageSize, CurPage = curPage, trans = {Num
    private int PageSize;
    private int CurPage;
    private List<TransEntity> trans;

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

    public List<TransEntity> getTrans() {
        return trans;
    }

    public void setTrans(List<TransEntity> trans) {
        this.trans = trans;
    }
}
