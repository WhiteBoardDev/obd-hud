package com.wbdev.obdhud.api;

import com.github.pires.obd.enums.AvailableCommandNames;

public class Metric {

    private final AvailableCommandNames metricName;
    private final Double value;
    private final String unit;
    private final String text;

    public Metric(AvailableCommandNames metricName, Double value, String unit, String text){
        this.metricName = metricName;
        this.value = value;
        this.unit = unit;
        this.text = text;
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

    public String getText() {
        return text;
    }
}
