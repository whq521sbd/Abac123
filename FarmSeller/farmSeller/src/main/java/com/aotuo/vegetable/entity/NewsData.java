package com.aotuo.vegetable.entity;

import java.util.List;

/**
 * Created by 牛XX on 2016/9/1.
 */
public class NewsData {
    private String NewsClass;
    private List<NewsListData> News;

    public List<NewsListData> getNews() {
        return News;
    }

    public void setNews(List<NewsListData> news) {
        News = news;
    }

    public String getNewsClass() {

        return NewsClass;
    }

    public void setNewsClass(String newsClass) {
        NewsClass = newsClass;
    }
}
