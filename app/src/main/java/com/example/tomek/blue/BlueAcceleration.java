package com.example.tomek.blue;

import android.util.Log;

/**
 * Created by Tomek on 2015-06-08.
 */
public class BlueAcceleration extends BluetoothValue{
    volatile private int accelerationValue = 0;
    private static BlueAcceleration __acceleration = null;

    private final static String typeName = "Przyspieszenie";

    public static BlueAcceleration getInstance(){
        return __acceleration == null ? new BlueAcceleration() : __acceleration;
    }

    private BlueAcceleration(){
        __acceleration = this;
    }

    public int getValue(){
        if(valueChanged){
            this.valueChanged = false;
            Log.i(TAG, "Acceleration value: " + this.accelerationValue);
            return this.accelerationValue;
        }else {
            return -1;
        }
    }

    public String getValueString(){
        String value = "";
        try{
            value = Integer.toString(this.accelerationValue);
        } catch (Exception e) {
            value = "error";
        }

        return value;
    }

    public void setValue(int value){
        this.valueChanged = true;
        this.accelerationValue = value;
    }

    public void setValue(String s){
        this.valueChanged = true;

        try{
            accelerationValue = Integer.parseInt(s);
        } catch (NumberFormatException e){
            this.valueChanged = false;
            Log.e(TAG, "[BlueAcceleration]Invalid parse from string to double in BlueAcceleration: " + e.getMessage());
        }
    }

    public static String getTypeName(){
        return typeName;
    }

    public void printValue(){
        Log.i(TAG, "Acceleration value: " + this.accelerationValue);
    }
}
