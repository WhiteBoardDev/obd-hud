package com.wbdev.obdhud.connectivity;

import com.github.pires.obd.commands.control.VinCommand;
import com.github.pires.obd.commands.protocol.EchoOffCommand;
import com.github.pires.obd.commands.protocol.LineFeedOffCommand;
import com.github.pires.obd.commands.protocol.TimeoutCommand;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Pattern;

@Component
public class ConnectionManager {

    private static final int BAUD_RATE = 38400;
    private static final int DATA_BITS = 8;
    private static final int STOP_BITS = 1;
    private SerialPort serialPort;
    private SocketInputStream socketInputStream;
    private SocketOutPutStream socketOutPutStream;

    public String[] getAvailablePorts(){
        return SerialPortList.getPortNames("/dev/", Pattern.compile("tty.*"));
    }

    public void connectToPort(String portName) throws SerialPortException, IOException, InterruptedException {
        this.serialPort = new SerialPort(portName);
        this.serialPort.openPort();
        this.serialPort.setParams(BAUD_RATE, DATA_BITS, STOP_BITS, 0, true, true);
        this.socketInputStream = new SocketInputStream(this.serialPort);
        this.socketOutPutStream = new SocketOutPutStream(this.serialPort);

        initObd(socketInputStream, socketOutPutStream);
    }

    private void initObd(InputStream in, OutputStream out) throws IOException, InterruptedException {
        new EchoOffCommand().run(in, out);
        new LineFeedOffCommand().run(in, out);
        new TimeoutCommand(125).run(in, out);
        VinCommand vinCommand = new VinCommand();
        vinCommand.run(in, out);
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
