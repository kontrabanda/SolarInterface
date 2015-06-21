package com.example.tomek.blue;

import android.util.Log;
import android.widget.Switch;

import java.util.concurrent.ExecutionException;

/**
 * Created by Tomek on 2015-06-07.
 */



public class BluetoothDescriptor {
    private String TAG = "BluetoothConnector";

    public void descript(String s) throws AdditionalException{
        s = s.replaceAll("n", "");

        String[] temp = s.split("@");
        int number = -1;
        try {
            number = Integer.parseInt(temp[0]);
        } catch (NumberFormatException e){
            number = -1;
            Log.e(TAG, "[BlueDescriptor]Invalid id parse: " + temp.length);
        }

        if(temp.length <= 1){
            throw new AdditionalException("Message too short");
        }

        BluetoothValue.valuesTypes type = BluetoothValue.valuesTypes.getAppropriateValue(number);

        switch(type){
            case ACCELERATION:
                BlueAcceleration blueAcc = BlueAcceleration.getInstance();
                blueAcc.setValue(temp[1]);
                break;
            case ANGLE:
                BlueAngle blueAngle = BlueAngle.getInstance();
                blueAngle.setValue(temp[1], temp[2], temp[3]);
                break;
            case BATTERY_VOLTAGE:
                BlueBatteryVoltage blueBVoltage = BlueBatteryVoltage.getInstance();
                blueBVoltage.setValue(temp[1]);
                break;
            case BATTERY_CURRENT:
                BlueBatteryCurrent blueBCurrent = BlueBatteryCurrent.getInstance();
                blueBCurrent.setValue(temp[1]);
                break;
            case SOLAR_VOLTAGE:
                BlueSolarVoltage blueSVoltage = BlueSolarVoltage.getInstance();
                blueSVoltage.setValue(temp[1]);
                break;
            case ENGINE_POWER:
                BlueEnginePower blueEPower = BlueEnginePower.getInstance();
                blueEPower.setValue(temp[1]);
                break;
            default:
                throw new AdditionalException("Message type = null, invalid id");
        }
    }

    private static BluetoothArduino __blue = null;
}
