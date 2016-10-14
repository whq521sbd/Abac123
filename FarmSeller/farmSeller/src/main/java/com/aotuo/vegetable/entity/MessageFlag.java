package com.aotuo.vegetable.entity;

import java.io.Serializable;

/**
 * Created by 牛XX on 2016/9/3.
 */
public class MessageFlag implements Serializable{
    private boolean hasNews;
    private boolean hasMsg;
    private boolean hasMess;

    public void clear(){
        hasNews = false;
        hasMsg = false;
        hasMess = false;
    }

    /**
     * 市场消息
     * @return
     */
    public boolean isHasMess() {
        return hasMess;
    }

    public void setHasMess(boolean hasMess) {
        this.hasMess = hasMess;
    }

    /**
     * 用户消息
     * @return
     */
    public boolean isHasMsg() {

        return hasMsg;
    }

    public void setHasMsg(boolean hasMsg) {
        this.hasMsg = hasMsg;
    }

    /**
     * 新闻
     * @return
     */
    public boolean isHasNews() {

        return hasNews;
    }

    public void setHasNews(boolean hasNews) {
        this.hasNews = hasNews;
    }
}
