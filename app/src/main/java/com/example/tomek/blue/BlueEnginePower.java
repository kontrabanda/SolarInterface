package com.example.tomek.blue;

import android.util.Log;

/**
 * Created by Tomek on 2015-06-14.
 */
public class BlueEnginePower extends BluetoothValue {
    volatile private double enginePowerValue = 0;

    private static BlueEnginePower __enginePower = null;

    public static BlueEnginePower getInstatnce(){
        return __enginePower == null ? new BlueEnginePower() : __enginePower;
    }

    public double getValue(){
        if(valueChanged){
            this.valueChanged = false;
            return this.enginePowerValue;
        }else {
            return -1;
        }
    }

    public void setValue(double value){
        this.valueChanged = true;

        this.enginePowerValue = value;
    }

    public void setValue(String s){
        this.valueChanged = true;

        try{
            enginePowerValue = Double.parseDouble(s);
        } catch (NumberFormatException e){
            this.valueChanged = false;
            Log.e(TAG, "Invalid parse from string to double in BlueSolarVoltage: " + e.getMessage());
        }
    }
}
