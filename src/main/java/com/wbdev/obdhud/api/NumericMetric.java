package com.wbdev.obdhud.api;

import com.github.pires.obd.enums.AvailableCommandNames;

public class NumericMetric {

    private AvailableCommandNames metricName;

    private Double value;

    private String unit;

    public NumericMetric(AvailableCommandNames metricName, Double value, String unit){
        this.metricName = metricName;
        this.value = value;
        this.unit = unit;
    }

    public AvailableCommandNames getMetricName() {
        return metricName;
    }

    public Double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }
}
