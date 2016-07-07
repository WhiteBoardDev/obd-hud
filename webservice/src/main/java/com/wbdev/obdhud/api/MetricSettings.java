package com.wbdev.obdhud.api;

import com.github.pires.obd.enums.AvailableCommandNames;

import java.util.Set;

public class MetricSettings {

    private Set<AvailableCommandNames> enabledMetrics;

    private Set<AvailableCommandNames> disabledMetrics;

    public MetricSettings(Set<AvailableCommandNames> enabledMetrics, Set<AvailableCommandNames> disabledMetrics){
        this.enabledMetrics = enabledMetrics;
        this.disabledMetrics = disabledMetrics;
    }

    public Set<AvailableCommandNames> getEnabledMetrics() {
        return enabledMetrics;
    }

    public Set<AvailableCommandNames> getDisabledMetrics() {
        return disabledMetrics;
    }
}
