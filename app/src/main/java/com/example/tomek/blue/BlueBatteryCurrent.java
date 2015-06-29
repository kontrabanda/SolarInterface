package com.example.tomek.blue;

import android.util.Log;

/**
 * Created by Tomek on 2015-06-14.
 */
public class BlueBatteryCurrent extends BluetoothValue{
    volatile private double batteryCurrentValue = 0;

    private final static String typeName = "Natezenie na baterii";

    private static BlueBatteryCurrent __batteryCurrent = null;

    public static BlueBatteryCurrent getInstance(){
        return __batteryCurrent == null ? new BlueBatteryCurrent() : __batteryCurrent;
    }

    private BlueBatteryCurrent(){
        __batteryCurrent = this;
    }

    public double getValue(){
        if(valueChanged){
            this.valueChanged = false;
            Log.i(TAG, "BatteryCurrent value: " + this.batteryCurrentValue);
            return this.batteryCurrentValue;
        }else {
            return -1;
        }
    }

    public void setValue(double value){
        this.valueChanged = true;
        this.batteryCurrentValue = value;
    }

    public void setValue(String s){
        this.valueChanged = true;

        try{
            batteryCurrentValue = Double.parseDouble(s);
        } catch (NumberFormatException e){
            this.valueChanged = false;
            Log.e(TAG, "[BlueBatteryCurrent]Invalid parse from string to double in BlueBatteryCurrent: " + e.getMessage());
        }
    }

    public void printValue(){
        Log.i(TAG, "BatteryCurrent value: " + this.batteryCurrentValue);
    }

    public static String getTypeName(){
        return typeName;
    }

    public String getValueString(){
        String value = "";
        try{
            value = Double.toString(this.batteryCurrentValue);
        } catch (Exception e) {
            value = "error";
        }

        return value;
    }
}
