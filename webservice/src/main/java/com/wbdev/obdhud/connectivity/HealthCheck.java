package com.wbdev.obdhud.connectivity;

import com.github.pires.obd.commands.protocol.EchoOffCommand;
import com.wbdev.obdhud.exceptionhandling.NotConnectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HealthCheck {

    @Autowired
    private CommandExecutor commandExecutor;

    public void echo() throws InterruptedException, NotConnectedException, IOException {
        commandExecutor.run(new EchoOffCommand());
    }

}
