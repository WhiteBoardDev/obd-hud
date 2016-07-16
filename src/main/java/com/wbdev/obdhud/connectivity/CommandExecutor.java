package com.wbdev.obdhud.connectivity;

import com.github.pires.obd.commands.ObdCommand;
import com.wbdev.obdhud.exceptionhandling.NotConnectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CommandExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandExecutor.class);

    @Autowired
    private ConnectionManager connectionManager;

    public void run(ObdCommand obdCommand) throws IOException, InterruptedException, NotConnectedException {
        if(!connectionManager.isConnected()){
            throw new NotConnectedException();
        }
        LOGGER.info("preparing to execute command" );
        obdCommand.run(connectionManager.getSocketInputStream(), connectionManager.getSocketOutPutStream());
        int dataLeft = connectionManager.getSocketInputStream().available();
        if(dataLeft > 0){
            LOGGER.warn("Data still left after command ran " + dataLeft);
            connectionManager.getSocketInputStream().skip(dataLeft);
        }

        LOGGER.info("completed command execution" );
    }
}