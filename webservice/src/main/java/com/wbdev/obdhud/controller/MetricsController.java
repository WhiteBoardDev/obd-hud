package com.wbdev.obdhud.controller;

import com.github.pires.obd.enums.AvailableCommandNames;
import com.wbdev.obdhud.api.NumericMetric;
import com.wbdev.obdhud.exceptionhandling.NotConnectedException;
import com.wbdev.obdhud.metrics.MetricSettingsService;
import com.wbdev.obdhud.metrics.NumericMetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/obdmetrics")
public class MetricsController {

    @Autowired
    private NumericMetricService numericMetricService;

    @Autowired
    private MetricSettingsService metricSettingsService;

    @RequestMapping(method = RequestMethod.GET)
    public List<NumericMetric> getMetrics() throws InterruptedException, IOException, NotConnectedException {
        List<NumericMetric> result = new LinkedList<>();
        for (AvailableCommandNames availableCommandNames : metricSettingsService.getEnabledMetrics()){
            result.add(numericMetricService.getMetric(availableCommandNames));
        }
        return result;
    }
}
