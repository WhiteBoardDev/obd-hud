package com.wbdev.obdhud.connectivity;

import jssc.SerialPort;
import jssc.SerialPortException;

import java.io.IOException;
import java.io.OutputStream;

public class SocketOutPutStream extends OutputStream {

    private SerialPort serialPort;

    SocketOutPutStream(SerialPort serialPort){
        this.serialPort = serialPort;
    }
    @Override
    public void write(int b) throws IOException {
        try {
            this.serialPort.writeInt(b);
        } catch (SerialPortException e) {
            throw new RuntimeException("Unable to write to socket", e);
        }
    }
}
