package com.wbdev.obdhud.api;

import com.github.pires.obd.enums.AvailableCommandNames;

public class NumericMetric {

    private AvailableCommandNames metricName;

    private int value;

    private String unit;

    public NumericMetric(AvailableCommandNames metricName, int value, String unit){
        this.metricName = metricName;
        this.value = value;
        this.unit = unit;
    }

    public AvailableCommandNames getMetricName() {
        return metricName;
    }

    public int getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }
}
