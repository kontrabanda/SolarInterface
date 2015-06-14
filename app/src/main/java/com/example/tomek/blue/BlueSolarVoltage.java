package com.example.tomek.blue;

import android.util.Log;

/**
 * Created by Tomek on 2015-06-14.
 */
public class BlueSolarVoltage extends BluetoothValue{
    volatile private double solarVoltageValue = 0;

    private static BlueSolarVoltage __solarVoltage = null;

    public static BlueSolarVoltage getInstatnce(){
        return __solarVoltage == null ? new BlueSolarVoltage() : __solarVoltage;
    }

    public double getValue(){
        if(valueChanged){
            this.valueChanged = false;
            return this.solarVoltageValue;
        }else {
            return -1;
        }
    }

    public void setValue(double value){
        this.valueChanged = true;

        this.solarVoltageValue = value;
    }

    public void setValue(String s){
        this.valueChanged = true;

        try{
            solarVoltageValue = Double.parseDouble(s);
        } catch (NumberFormatException e){
            this.valueChanged = false;
            Log.e(TAG, "Invalid parse from string to double in BlueSolarVoltage: " + e.getMessage());
        }
    }
}
