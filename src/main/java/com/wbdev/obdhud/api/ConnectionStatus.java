package com.wbdev.obdhud.api;

public class ConnectionStatus {

    private String[] availablePorts;

    private String connectedPort;

    private boolean isConnected;

    public String[] getAvailablePorts() {
        return availablePorts;
    }

    public void setAvailablePorts(String[] availablePorts) {
        this.availablePorts = availablePorts;
    }

    public String getConnectedPort() {
        return connectedPort;
    }

    public void setConnectedPort(String connectedPort) {
        this.connectedPort = connectedPort;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }
}
