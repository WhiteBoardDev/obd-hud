package com.wbdev.obdhud.api;

import com.github.pires.obd.enums.AvailableCommandNames;

public class StringMetric {

    private AvailableCommandNames metricName;

    private String value;

    public StringMetric(AvailableCommandNames metricName, String value) {
        this.metricName = metricName;
        this.value = value;
    }

    public AvailableCommandNames getMetricName() {
        return metricName;
    }

    public String getValue() {
        return value;
    }
}
