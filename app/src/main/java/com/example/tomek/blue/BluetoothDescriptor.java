package com.example.tomek.blue;

import android.util.Log;
import android.widget.Switch;

import java.util.concurrent.ExecutionException;

/**
 * Created by Tomek on 2015-06-07.
 */



public class BluetoothDescriptor {
    private String TAG = "BluetoothConnector";

    public void descript(String s) throws Exception{

        String[] temp = s.split("|");
        int number = -1;
        try {
            number = Integer.parseInt(temp[0]);
        } catch (NumberFormatException e){
            Log.e(TAG, "Invalid id parse: " + e.getMessage());
        }


        if(temp.length == 1){
            throw new AdditionalException("Message too short");
        }

        BluetoothValue.valuesTypes type = BluetoothValue.valuesTypes.getAppropriateValue(number);

        switch(type){
            case ACCELERATION:

                break;
            case ANGLE:

                break;
            case BATTERY_VOLTAGE:

                break;
            case BATTERY_CURRENT:

                break;
            case SOLAR_VOLTAGE:

                break;
            case ENGINE_POWER:

                break;
            default:
                throw new AdditionalException("Message type = null, invalid id");
        }
    }

    private static BluetoothArduino __blue = null;
}
