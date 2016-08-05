package com.wbdev.obdhud.metrics;

import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.enums.AvailableCommandNames;
import com.github.pires.obd.exceptions.NoDataException;
import com.wbdev.obdhud.api.Metric;
import com.wbdev.obdhud.connectivity.CommandExecutor;
import com.wbdev.obdhud.exceptionhandling.NotConnectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MetricService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MetricService.class);

    @Autowired
    private CommandExecutor commandExecutor;

    @Autowired
    private CommandFactory commandFactory;

    public Metric getMetric(AvailableCommandNames availableCommandName) throws InterruptedException, NotConnectedException, IOException {
        Metric metric;
        ObdCommand obdCommand = commandFactory.createCommand(availableCommandName);
        if(obdCommand != null) {
            try {
                try {
                    commandExecutor.run(obdCommand);

                    String result = obdCommand.getCalculatedResult();
                    Double numbericResult = null;
                    try {
                        numbericResult = Double.parseDouble(result);
                    }catch (NumberFormatException ex) {
                        //NOOP
                    }

                    metric = new Metric(availableCommandName,
                            numbericResult,
                            obdCommand.getResultUnit(),
                            result);
                } catch (NoDataException noDataEx) {
                    metric = new Metric(availableCommandName, -1.0, "NO DATA", "NO DATA");
                    LOGGER.warn("No Data Returned from command " + obdCommand.getName());
                }

            }
            catch (Exception exception) {
                LOGGER.error("Error executing command " + availableCommandName.name(), exception);
                metric = new Metric(availableCommandName, -1.0, "ERROR", "ERROR");
            }
        }
        else {
            metric = new Metric(availableCommandName, -1.0, "NA", "NA");
        }

        return metric;
    }
}
