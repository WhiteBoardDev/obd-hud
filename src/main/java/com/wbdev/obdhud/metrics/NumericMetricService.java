package com.wbdev.obdhud.metrics;

import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.enums.AvailableCommandNames;
import com.github.pires.obd.exceptions.NonNumericResponseException;
import com.wbdev.obdhud.api.NumericMetric;
import com.wbdev.obdhud.connectivity.CommandExecutor;
import com.wbdev.obdhud.exceptionhandling.NotConnectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NumericMetricService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NumericMetricService.class);

    @Autowired private CommandExecutor commandExecutor;

    @Autowired private CommandFactory commandFactory;

    public NumericMetric getMetric(AvailableCommandNames availableCommandName) throws InterruptedException, NotConnectedException, IOException {
        NumericMetric metric = null;
        ObdCommand obdCommand = commandFactory.createCommand(availableCommandName);
        if(obdCommand != null) {
            try {
                commandExecutor.run(obdCommand);
                metric = new NumericMetric(availableCommandName,
                        Double.parseDouble(obdCommand.getCalculatedResult()),
                        obdCommand.getResultUnit());
            }
            catch (Exception exception) {
                LOGGER.error("Error executing command", exception);
                metric = new NumericMetric(availableCommandName, -1.0, "ERROR");
            }
        }
        else {
            metric = new NumericMetric(availableCommandName, -1.0, "NA");
        }

        return metric;
    }
}
