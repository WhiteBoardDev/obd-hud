package com.wbdev.obdhud.connectivity;

import com.github.pires.obd.commands.ObdCommand;
import com.wbdev.obdhud.exceptionhandling.NotConnectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CommandExecutor {

    @Autowired
    private ConnectionManager connectionManager;

    public void run(ObdCommand obdCommand) throws IOException, InterruptedException, NotConnectedException {
        if(!connectionManager.isConnected()){
            throw new NotConnectedException();
        }
        obdCommand.run(connectionManager.getSocketInputStream(), connectionManager.getSocketOutPutStream());
    }
    
}
