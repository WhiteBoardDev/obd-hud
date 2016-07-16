package com.wbdev.obdhud.connectivity;

import jssc.SerialPort;
import jssc.SerialPortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;

public class SocketOutPutStream extends OutputStream {

    private static final Logger LOGGER = LoggerFactory.getLogger(SocketOutPutStream.class);
    private SerialPort serialPort;

    SocketOutPutStream(SerialPort serialPort){
        this.serialPort = serialPort;
    }
    @Override
    public void write(int b) throws IOException {
        try {
            char log = (char)b;
            if(log == '\r') {
                log = 'r';
            }
            LOGGER.info("Writing " + log);
            this.serialPort.writeInt(b);
        } catch (SerialPortException e) {
            throw new RuntimeException("Unable to write to socket", e);
        }
    }
}
