package com.example.tomek.blue;

import android.util.Log;

/**
 * Created by Tomek on 2015-06-08.
 */
public class BlueAcceleration extends BluetoothValue{
    volatile private int accelerationValue = 0;
    private static BlueAcceleration __acceleration = null;

    public static BlueAcceleration getInstatnce(){
        return __acceleration == null ? new BlueAcceleration() : __acceleration;
    }

    public int getValue(){
        if(valueChanged){
            this.valueChanged = false;
            return this.accelerationValue;
        }else {
            return -1;
        }
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
            Log.e(TAG, "Invalid parse from string to double in BlueAcceleration: " + e.getMessage());
        }
    }
}
