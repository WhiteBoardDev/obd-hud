# OBD HUD

obd-hud is an ODB II scanner and reader. Its designed to help diagnose automotive issues and can also be used to
monitor performance metrics.

This application uses [obd-java-api](https://github.com/pires/obd-java-api) and [jssc](https://github.com/scream3r/java-simple-serial-connector) to connect to an ELM 327 device.

![currentStatus](https://github.com/WhiteBoardDev/obd-hud/blob/master/doc/screen_shot_2016_08_04.png)

## Features

- Live updating charts to display numeric metrics
- Customize what data goes in the charts
- Live updating table which shows all available data
- Simple drop down box for connectivity
- REST API for extensibility

## Current Status

There are many TODOs for this project but the basic structure is there. Currently it works with the `obdsim` and needs to be
tested on other devices and cars.

### Work needed

- ~~Enforce serial command execution. We can trigger concurrent commands at this point that mess with ELM and corrupt communication~~
- Support for failure codes and code reset
- test with other cars
- nail down initialization sequence. `ConnectionManager.initObd` issues some start up commands to init the connection. I need to confirm these
 are correct using an actual car.

## Testing and simulator

### Installing simulator

Go to the downloads page

https://icculus.org/obdgpslogger/#Download

and download the tallball of the simulator

from there extract the tarball and run the following commands within the extracted folder

```
mkdir build
cd build
cmake ..
make
make install
```

Next run `obdsim` to start the simulator. It will print out the port which the simulator will communicate on.

### Running the application

    ./gradlew clean bootRun

Then browse to `http://localhost:8080`

### API Documentation

Api docs can be found at `http://localhost:8080/swagger-ui.html`