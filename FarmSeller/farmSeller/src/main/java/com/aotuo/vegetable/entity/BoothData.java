package com.aotuo.vegetable.entity;

import java.util.List;

/**
 * Created by 牛XX on 2016/9/24.
 */

public class BoothData {
    //返回：PageSize = PageSize, CurPage = curPage ,Booths=new List
    private List<BoothEntity> Booths;
    private int PageSize;
    private int CurPage;

    public List<BoothEntity> getBooths() {
        return Booths;
    }

    public void setBooths(List<BoothEntity> booths) {
        Booths = booths;
    }

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
}
