package com.aotuo.vegetable.entity;

/**
 * Created by 牛XX on 2016/8/27.
 */
public class KeyValueData {
    private String Key;
    private String Value;

    public KeyValueData() {
    }

    public KeyValueData(String key, String value) {
        Key = key;
        Value = value;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getKey() {

        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }
}
