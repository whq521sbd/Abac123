package com.aotuo.vegetable.entity;

import java.util.List;

/**
 * Created by 牛XX on 2016/8/26.
 */
public class AreaData {
    private String Ver;
    private List<KeyValueData> Province;
    private List<KeyValueData> City;
    private List<KeyValueData> Area;

    public List<KeyValueData> getProvince() {
        return Province;
    }

    public void setProvince(List<KeyValueData> province) {
        Province = province;
    }

    public List<KeyValueData> getCity() {
        return City;
    }

    public void setCity(List<KeyValueData> city) {
        City = city;
    }

    public List<KeyValueData> getArea() {
        return Area;
    }

    public void setArea(List<KeyValueData> area) {
        Area = area;
    }

    public String getVer() {

        return Ver;
    }

    public void setVer(String ver) {
        Ver = ver;
    }
}
