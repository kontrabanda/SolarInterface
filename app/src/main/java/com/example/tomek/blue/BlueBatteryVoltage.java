package com.example.tomek.blue;

import android.util.Log;

/**
 * Created by Tomek on 2015-06-14.
 */
public class BlueBatteryVoltage extends BluetoothValue{
    volatile private double batteryVoltageValue = 0;

    private static BlueBatteryVoltage __batteryVoltage = null;

    public static BlueBatteryVoltage getInstatnce(){
        return __batteryVoltage == null ? new BlueBatteryVoltage() : __batteryVoltage;
    }

    public double getValue(){
        if(valueChanged){
            this.valueChanged = false;
            return this.batteryVoltageValue;
        }else {
            return -1;
        }
    }

    public void setValue(double value){
        this.valueChanged = true;

        this.batteryVoltageValue = value;
    }

    public void setValue(String s){
        this.valueChanged = true;

        try{
            batteryVoltageValue = Double.parseDouble(s);
        } catch (NumberFormatException e){
            this.valueChanged = false;
            Log.e(TAG, "Invalid parse from string to double in BlueBatteryVoltage: " + e.getMessage());
        }
    }
}
