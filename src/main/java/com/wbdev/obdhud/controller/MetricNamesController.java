package com.wbdev.obdhud.controller;

import com.github.pires.obd.enums.AvailableCommandNames;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/obdmetricnames")
public class MetricNamesController {

    @RequestMapping(method = RequestMethod.GET)
    public AvailableCommandNames[] getAllMetricNames(){
        return AvailableCommandNames.values();
    }
}
