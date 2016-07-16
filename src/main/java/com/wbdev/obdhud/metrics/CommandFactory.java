package com.wbdev.obdhud.metrics;

import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.commands.SpeedCommand;
import com.github.pires.obd.commands.control.*;
import com.github.pires.obd.commands.engine.*;
import com.github.pires.obd.commands.fuel.*;
import com.github.pires.obd.commands.pressure.BarometricPressureCommand;
import com.github.pires.obd.commands.pressure.FuelPressureCommand;
import com.github.pires.obd.commands.pressure.FuelRailPressureCommand;
import com.github.pires.obd.commands.pressure.IntakeManifoldPressureCommand;
import com.github.pires.obd.commands.protocol.*;
import com.github.pires.obd.commands.temperature.AirIntakeTemperatureCommand;
import com.github.pires.obd.commands.temperature.AmbientAirTemperatureCommand;
import com.github.pires.obd.commands.temperature.EngineCoolantTemperatureCommand;
import com.github.pires.obd.enums.AvailableCommandNames;
import org.springframework.stereotype.Component;

/**
 * Created by evanharris on 7/15/16.
 */
@Component
public class CommandFactory {

    public ObdCommand createCommand(AvailableCommandNames availableCommandName) {
        ObdCommand result = null;
        switch (availableCommandName) {
            case AIR_INTAKE_TEMP:
                result = new AirIntakeTemperatureCommand();
                break;
            case AMBIENT_AIR_TEMP:
                result = new AmbientAirTemperatureCommand();
                break;
            case ENGINE_COOLANT_TEMP:
                result = new EngineCoolantTemperatureCommand();
                break;
            case BAROMETRIC_PRESSURE:
                result = new BarometricPressureCommand();
                break;
            case FUEL_PRESSURE:
                result = new FuelPressureCommand();
                break;
            case INTAKE_MANIFOLD_PRESSURE:
                result = new IntakeManifoldPressureCommand();
                break;
            case ENGINE_LOAD:
                result = new LoadCommand();
                break;
            case ENGINE_RUNTIME:
                result = new RuntimeCommand();
                break;
            case ENGINE_RPM:
                result = new RPMCommand();
                break;
            case SPEED:
                result = new SpeedCommand();
                break;
            case MAF:
                result = new MassAirFlowCommand();
                break;
            case THROTTLE_POS:
                result = new ThrottlePositionCommand();
                break;
            case TROUBLE_CODES:
                result = new TroubleCodesCommand();
                break;
            case PENDING_TROUBLE_CODES:
                result = new PendingTroubleCodesCommand();
                break;
            case PERMANENT_TROUBLE_CODES:
                result = new PermanentTroubleCodesCommand();
                break;
            case FUEL_LEVEL:
                result = new FuelLevelCommand();
                break;
            case FUEL_TYPE:
                result = new FindFuelTypeCommand();
                break;
            case FUEL_CONSUMPTION_RATE:
                result = new ConsumptionRateCommand();
                break;
            case TIMING_ADVANCE:
                result = new TimingAdvanceCommand();
                break;
            case DTC_NUMBER:
                result = new DtcNumberCommand();
                break;
            case EQUIV_RATIO:
                result = new EquivalentRatioCommand();
                break;
            case DISTANCE_TRAVELED_AFTER_CODES_CLEARED:
                result = new DistanceSinceCCCommand();
                break;
            case CONTROL_MODULE_VOLTAGE:
                result = new ModuleVoltageCommand();
                break;
            case ENGINE_FUEL_RATE:
                result = new FuelTrimCommand();
                break;
            case FUEL_RAIL_PRESSURE:
                result = new FuelRailPressureCommand();
                break;
            case VIN:
                result = new VinCommand();
                break;
            case DISTANCE_TRAVELED_MIL_ON:
                result = new DistanceMILOnCommand();
                break;
            case TIME_TRAVELED_MIL_ON:
                //TODO
                break;
            case TIME_SINCE_TC_CLEARED:
                //TODO
                break;
            case REL_THROTTLE_POS:
                result = new ThrottlePositionCommand();
                break;
            case PIDS_01_20:
                result = new AvailablePidsCommand_01_20();
                break;
            case PIDS_21_40:
                result = new AvailablePidsCommand_21_40();
                break;
            case PIDS_41_60:
                result = new AvailablePidsCommand_41_60();
                break;
            case ABS_LOAD:
                result = new AbsoluteLoadCommand();
                break;
            case ENGINE_OIL_TEMP:
                result = new OilTempCommand();
                break;
            case AIR_FUEL_RATIO:
                result = new AirFuelRatioCommand();
                break;
            case WIDEBAND_AIR_FUEL_RATIO:
                result = new WidebandAirFuelRatioCommand();
                break;
            case DESCRIBE_PROTOCOL:
                result = new DescribeProtocolCommand();
                break;
            case DESCRIBE_PROTOCOL_NUMBER:
                result = new DescribeProtocolNumberCommand();
                break;
            case IGNITION_MONITOR:
                result = new IgnitionMonitorCommand();
                break;

        }

        return result;

    }
}
