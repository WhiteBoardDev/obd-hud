package com.wbdev.obdhud.controller;


import com.wbdev.obdhud.api.MetricSettings;
import com.wbdev.obdhud.metrics.MetricSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/metricsettings")
public class MetricSettingsController {

    @Autowired
    private MetricSettingsService metricSettingsService;

    @RequestMapping( method = RequestMethod.GET)
    public MetricSettings getMetricSettings(){
        return this.metricSettingsService.getMetricSettings();
    }


    @RequestMapping( method = RequestMethod.PUT)
    public void updateMetricSettings(@RequestBody MetricSettings metricSettings){
        this.metricSettingsService.updateMetricSettings(metricSettings);
    }

}
