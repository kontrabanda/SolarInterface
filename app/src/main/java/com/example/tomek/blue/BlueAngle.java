package com.example.tomek.blue;

import android.nfc.Tag;
import android.util.Log;

/**
 * Created by Tomek on 2015-06-14.
 */
public class BlueAngle extends BluetoothValue {
    volatile private double angleXValue = 0;
    volatile private double angleYValue = 0;
    volatile private double angleZValue = 0;

    private static BlueAngle __angle = null;

    public static BlueAngle getInstance(){
        return __angle == null ? new BlueAngle() : __angle;
    }

    public double getValueX(){
        if(valueChanged){
            this.valueChanged = false;
            Log.i(TAG, "AngleX value: " + this.angleXValue);
            return this.angleXValue;
        }else {
            return -1;
        }
    }

    public double getValueY(){
        if(valueChanged){
            this.valueChanged = false;
            Log.i(TAG, "AngleY value: " + this.angleYValue);
            return this.angleYValue;
        }else {
            return -1;
        }
    }

    public double getValueZ(){
        if(valueChanged){
            this.valueChanged = false;
            Log.i(TAG, "AngleZ value: " + this.angleZValue);
            return this.angleZValue;
        }else {
            return -1;
        }
    }

    public void setValue(double x, double y, double z){
        this.valueChanged = true;

        this.angleXValue = x;
        this.angleYValue = y;
        this.angleZValue = z;
    }

    public void setValue(String x, String y, String z){
        this.valueChanged = true;

        try{
            angleXValue = Double.parseDouble(x);
            angleYValue = Double.parseDouble(y);
            angleZValue = Double.parseDouble(z);
        } catch (NumberFormatException e){
            this.valueChanged = false;
            Log.e(TAG, "Invalid parse from string to double in BlueAngle: " + e.getMessage());
        }
    }

    public void printValue(){
        Log.i(TAG, "AngleX value: " + this.angleXValue);
        Log.i(TAG, "AngleY value: " + this.angleYValue);
        Log.i(TAG, "AngleZ value: " + this.angleZValue);
    }
}
