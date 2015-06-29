package com.example.tomek.blue;

import android.util.Log;

/**
 * Created by Tomek on 2015-06-14.
 */
public class BlueEnginePower extends BluetoothValue {
    volatile private double enginePowerValue = 0;

    private final static String typeName = "Moc silnika";

    private static BlueEnginePower __enginePower = null;

    public static BlueEnginePower getInstance(){
        return __enginePower == null ? new BlueEnginePower() : __enginePower;
    }

    private BlueEnginePower(){
        __enginePower = this;
    }

    public double getValue(){
        if(valueChanged){
            this.valueChanged = false;
            Log.i(TAG, "EnginePower value: " + this.enginePowerValue);
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
            this.enginePowerValue = Integer.parseInt(s);
        } catch (NumberFormatException e){
            this.valueChanged = false;
            Log.e(TAG, "[BlueEnginePower]:Invalid parse from string to double in BlueSolarVoltage: " + e.getMessage());
        }
    }

    public void printValue(){
        Log.i(TAG, "EnginePower value: " + this.enginePowerValue);
    }

    public static String getTypeName(){
        return typeName;
    }

    public String getValueString(){
        String value = "";
        try{
            value = Double.toString(this.enginePowerValue);
        } catch (Exception e) {
            value = "error";
        }

        return value;
    }
}
