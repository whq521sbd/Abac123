package com.aotuo.vegetable.entity;

import java.util.List;

/**
 * Created by 牛XX on 2016/9/1.
 */
public class NewsTabData {
    private String Ver;
    private List<KeyValueData> Classes;

    public List<KeyValueData> getClasses() {
        return Classes;
    }

    public void setClasses(List<KeyValueData> classes) {
        Classes = classes;
    }

    public String getVer() {

        return Ver;
    }

    public void setVer(String ver) {
        Ver = ver;
    }
}
