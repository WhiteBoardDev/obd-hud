package com.wbdev.obdhud.connectivity;

import jssc.SerialPort;
import jssc.SerialPortException;

import java.io.IOException;
import java.io.InputStream;

public class SocketInputStream extends InputStream {

    private SerialPort serialPort;

    private int[] buffer = null;
    private int bufferIndex = 0;
    public SocketInputStream(SerialPort serialPort){
        this.serialPort = serialPort;
    }

    @Override
    public int read() throws IOException {
        int returnValue = -1;
        if(buffer == null || bufferIndex >= buffer.length){
            try {
                buffer = this.serialPort.readIntArray();
            } catch (SerialPortException e) {
                throw new RuntimeException(e);
            }
            bufferIndex = 0;
        }

        if(buffer != null){
            returnValue = buffer[bufferIndex++];
        }

        return returnValue;
    }
}
