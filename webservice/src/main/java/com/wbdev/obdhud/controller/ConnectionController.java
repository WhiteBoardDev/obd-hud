package com.wbdev.obdhud.controller;

import com.wbdev.obdhud.api.ConnectionRequest;
import com.wbdev.obdhud.api.ConnectionStatus;
import com.wbdev.obdhud.connectivity.ConnectionManager;
import jssc.SerialPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/connection")
public class ConnectionController {

    @Autowired
    private ConnectionManager connectionManager;

    @RequestMapping(method = RequestMethod.GET)
    public ConnectionStatus getConnectionStatus(){
        ConnectionStatus connectionStatus = new ConnectionStatus();
        connectionStatus.setAvailablePorts(connectionManager.getAvailablePorts());
        connectionStatus.setConnectedPort(connectionManager.getConnectedPortName());
        connectionStatus.setConnected(connectionManager.isConnected());
        return connectionStatus;
    }

    @RequestMapping(path = "/connect", method = RequestMethod.POST)
    public void connect(@RequestBody ConnectionRequest connectionRequest) throws InterruptedException, SerialPortException, IOException {
        connectionManager.connectToPort(connectionRequest.getPort());
    }
}
