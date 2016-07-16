# OBD HUD


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