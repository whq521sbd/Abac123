package com.aotuo.vegetable.entity;

/**
 * Created by 牛XX on 2016/9/21.
 */

public class LogInfoEntity {
    //返回:new { LogDis="",LogRate=""} });
    private double LogDis;
    private double LogRate;

    public double getLogRate() {
        return LogRate;
    }

    public void setLogRate(double logRate) {
        LogRate = logRate;
    }

    public double getLogDis() {

        return LogDis;
    }

    public void setLogDis(double logDis) {
        LogDis = logDis;
    }
}
