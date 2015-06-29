package com.example.tomek.blue;

import android.util.Log;

/**
 * Created by Tomek on 2015-06-14.
 */
public class BlueSolarVoltage extends BluetoothValue{
    volatile private double solarVoltageValue = 0;

    private final static String typeName = "Napiecie na panelach";

    private static BlueSolarVoltage __solarVoltage = null;

    public static BlueSolarVoltage getInstance(){
        return __solarVoltage == null ? new BlueSolarVoltage() : __solarVoltage;
    }

    private BlueSolarVoltage(){
        __solarVoltage = this;
    }

    public double getValue(){
        if(valueChanged){
            this.valueChanged = false;
            Log.i(TAG, "SolarVoltage value: " + this.solarVoltageValue);
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
            Log.e(TAG, "[BlueSolarVoltage]Invalid parse from string to double in BlueSolarVoltage: " + e.getMessage());
        }
    }

    public String getValueString(){
        String value = "";
        try{
            value = Double.toString(this.solarVoltageValue);
        } catch (Exception e) {
            value = "error";
        }

        return value;
    }

    public void printValue(){
        Log.i(TAG, "SolarVoltage value: " + this.solarVoltageValue);
    }

    public static String getTypeName(){
        return typeName;
    }
}
