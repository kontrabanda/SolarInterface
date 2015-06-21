package com.example.tomek.blue;

import android.util.Log;

/**
 * Created by Tomek on 2015-06-14.
 */
public class BlueBatteryVoltage extends BluetoothValue{
    volatile private double batteryVoltageValue = 0;

    private static BlueBatteryVoltage __batteryVoltage = null;

    public static BlueBatteryVoltage getInstance(){
        return __batteryVoltage == null ? new BlueBatteryVoltage() : __batteryVoltage;
    }

    private BlueBatteryVoltage(){
        __batteryVoltage = this;
    }

    public double getValue(){
        if(valueChanged){
            this.valueChanged = false;
            Log.i(TAG, "BatteryVoltage value: " + this.batteryVoltageValue);
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
            Log.e(TAG, "[BlueBatteryVoltage]Invalid parse from string to double in BlueBatteryVoltage: " + e.getMessage());
        }
    }

    public void printValue(){
        Log.i(TAG, "BatteryVoltage value: " + this.batteryVoltageValue);
    }
}
