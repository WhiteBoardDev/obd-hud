package com.wbdev.obdhud.connectivity;

import jssc.SerialPort;
import jssc.SerialPortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class SocketInputStream extends InputStream {

    private static final Logger LOGGER = LoggerFactory.getLogger(SocketInputStream.class);

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
                Thread.sleep(100);
                buffer = this.serialPort.readIntArray();
                if(buffer != null) {
                    char[] charOutput = new char[buffer.length];
                    for(int i = 0; i < charOutput.length; i++){
                        charOutput[i] = (char)buffer[i];
                    }
                    LOGGER.info("Read data " + Arrays.toString(charOutput).replace('\r', 'r'));
                }
                else {
                    LOGGER.info("No data to read");
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            bufferIndex = 0;
        }

        if(buffer != null){
            returnValue = buffer[bufferIndex++];
        }

        return returnValue;
    }

    @Override
    public int available() throws IOException {
        try {
            return this.serialPort.getInputBufferBytesCount();
        } catch (SerialPortException e) {
            throw new IOException(e);
        }
    }

}
