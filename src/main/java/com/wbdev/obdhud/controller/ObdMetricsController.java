package com.wbdev.obdhud.controller;

import com.github.pires.obd.enums.AvailableCommandNames;
import com.wbdev.obdhud.api.Metric;
import com.wbdev.obdhud.exceptionhandling.NotConnectedException;
import com.wbdev.obdhud.metrics.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/obdmetrics")
public class ObdMetricsController {

    @Autowired
    private MetricService metricService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Metric> getMetrics() throws InterruptedException, IOException, NotConnectedException {
        List<Metric> result = new LinkedList<>();
        for (AvailableCommandNames availableCommandNames : AvailableCommandNames.values()){
            result.add(metricService.getMetric(availableCommandNames));
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{metric}")
    public Metric getMetric(@PathVariable AvailableCommandNames metric) throws InterruptedException, IOException, NotConnectedException {
        return metricService.getMetric(metric);
    }
}
