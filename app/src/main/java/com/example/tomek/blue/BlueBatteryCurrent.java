package com.example.tomek.blue;

import android.util.Log;

/**
 * Created by Tomek on 2015-06-14.
 */
public class BlueBatteryCurrent extends BluetoothValue{
    volatile private double batteryCurrentValue = 0;

    private static BlueBatteryCurrent __batteryCurrent = null;

    public static BlueBatteryCurrent getInstatnce(){
        return __batteryCurrent == null ? new BlueBatteryCurrent() : __batteryCurrent;
    }

    public double getValue(){
        if(valueChanged){
            this.valueChanged = false;
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
            Log.e(TAG, "Invalid parse from string to double in BlueBatteryCurrent: " + e.getMessage());
        }
    }
}
