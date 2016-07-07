package com.wbdev.obdhud.metrics;

import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.commands.engine.RPMCommand;
import com.github.pires.obd.enums.AvailableCommandNames;
import com.wbdev.obdhud.api.NumericMetric;
import com.wbdev.obdhud.connectivity.CommandExecutor;
import com.wbdev.obdhud.exceptionhandling.NotConnectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class NumericMetricService {

    private Map<AvailableCommandNames, ObdCommand> obdCommands;

    @Autowired private CommandExecutor commandExecutor;

    public NumericMetricService(){
        obdCommands = new HashMap<>();
        obdCommands.put(AvailableCommandNames.ENGINE_RPM, new RPMCommand());
    }

    public NumericMetric getMetric(AvailableCommandNames availableCommandNames) throws InterruptedException, NotConnectedException, IOException {
        NumericMetric result = null;
        if(obdCommands.containsKey(availableCommandNames)){
            ObdCommand obdCommand = obdCommands.get(availableCommandNames);
            commandExecutor.run(obdCommand);
            result = new NumericMetric(availableCommandNames,
                    Integer.parseInt(obdCommand.getCalculatedResult()),
                    obdCommand.getResultUnit());
        }
        return result;
    }
}
