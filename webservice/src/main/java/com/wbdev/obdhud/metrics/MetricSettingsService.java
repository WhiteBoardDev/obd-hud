package com.wbdev.obdhud.metrics;

import com.github.pires.obd.enums.AvailableCommandNames;
import com.wbdev.obdhud.api.MetricSettings;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MetricSettingsService {

    private Set<AvailableCommandNames> enabledMetrics;

    private Set<AvailableCommandNames> disabledMetrics;

    public MetricSettingsService() {
        this.enabledMetrics = new HashSet<>();
        this.disabledMetrics = new HashSet<>();
        for(AvailableCommandNames availableCommandNames : AvailableCommandNames.values()){
            this.disabledMetrics.add(availableCommandNames);
        }
    }

    public Set<AvailableCommandNames> getEnabledMetrics(){
        return Collections.unmodifiableSet(this.enabledMetrics);
    }

    public MetricSettings getMetricSettings(){
        return new MetricSettings(Collections.unmodifiableSet(enabledMetrics),
                Collections.unmodifiableSet(disabledMetrics));
    }

    public void updateMetricSettings(MetricSettings metricSettings){
        if(metricSettings.getEnabledMetrics().size() + metricSettings.getDisabledMetrics().size() != AvailableCommandNames.values().length){
            throw new IllegalArgumentException("All metrics must be either disabled or enabled");
        }
        this.enabledMetrics = metricSettings.getEnabledMetrics();
        this.disabledMetrics = metricSettings.getDisabledMetrics();
    }

}
