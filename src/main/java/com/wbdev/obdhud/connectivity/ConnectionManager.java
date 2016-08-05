package com.wbdev.obdhud.connectivity;

import com.github.pires.obd.commands.protocol.*;
import com.github.pires.obd.enums.ObdProtocols;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.locks.Lock;
import java.util.regex.Pattern;

@Component
public class ConnectionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionManager.class);

    private static final int BAUD_RATE = 38400;
    private static final int DATA_BITS = 8;
    private static final int STOP_BITS = 1;
    private SerialPort serialPort;
    private SocketInputStream socketInputStream;
    private SocketOutPutStream socketOutPutStream;

    @Autowired
    private Lock lock;

    public String[] getAvailablePorts(){
        return SerialPortList.getPortNames("/dev/", Pattern.compile("tty.*"));
    }

    public void connectToPort(String portName) throws SerialPortException, IOException, InterruptedException {
        if(this.serialPort != null && this.serialPort.isOpened()) {
            this.serialPort.closePort();
        }
        this.serialPort = new SerialPort(portName);
        this.serialPort.openPort();
        this.serialPort.setParams(BAUD_RATE, DATA_BITS, STOP_BITS, 0, true, true);
        this.socketInputStream = new SocketInputStream(this.serialPort);
        this.socketOutPutStream = new SocketOutPutStream(this.serialPort);

        initObd(socketInputStream, socketOutPutStream);
    }

    private void initObd(InputStream in, OutputStream out) throws IOException, InterruptedException {
        lock.lock();
        try {
            new ObdResetCommand().run(in, out);
            new EchoOffCommand().run(in, out);
            new LineFeedOffCommand().run(in, out);
            new TimeoutCommand(255).run(in, out);
            new SelectProtocolCommand(ObdProtocols.AUTO).run(in, out);
        } finally {
            lock.unlock();
        }
    }

    public boolean isConnected(){
        return this.serialPort != null && this.serialPort.isOpened();
    }

    SocketInputStream getSocketInputStream(){
        return this.socketInputStream;
    }

    SocketOutPutStream getSocketOutPutStream(){
        return this.socketOutPutStream;
    }

    public String getConnectedPortName(){
        String connectedPort = null;
        if(this.serialPort != null){
            connectedPort = this.serialPort.getPortName();
        }
        return connectedPort;
    }

}
