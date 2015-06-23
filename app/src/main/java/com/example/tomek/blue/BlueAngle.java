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

    private final static String typeNameX = "Kat X ";
    private final static String typeNameY = "Kat Y";
    private final static String typeNameZ = "Kat Z";

    private static BlueAngle __angle = null;

    public static BlueAngle getInstance(){
        return __angle == null ? new BlueAngle() : __angle;
    }

    private BlueAngle(){
        __angle = this;
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

    public String getValueXString(){
        String value = "";
        try{
            value = Double.toString(this.angleXValue);
        } catch (Exception e) {
            value = "error";
        }

        return value;
    }

    public String getValueYString(){
        String value = "";
        try{
            value = Double.toString(this.angleYValue);
        } catch (Exception e) {
            value = "error";
        }

        return value;
    }

    public String getValueZString(){
        String value = "";
        try{
            value = Double.toString(this.angleZValue);
        } catch (Exception e) {
            value = "error";
        }

        return value;
    }

    public void setValue(String x, String y, String z){
        this.valueChanged = true;

        try{
            this.angleXValue = Double.parseDouble(x);
            this.angleYValue = Double.parseDouble(y);
            this.angleZValue = Double.parseDouble(z);
        } catch (NumberFormatException e){
            this.valueChanged = false;
            Log.e(TAG, "[BlueAngle]Invalid parse from string to double in BlueAngle: " + e.getMessage());
        }
    }

    public void printValue(){
        Log.i(TAG, "AngleX value: " + this.angleXValue);
        Log.i(TAG, "AngleY value: " + this.angleYValue);
        Log.i(TAG, "AngleZ value: " + this.angleZValue);
    }

    public String getTypeNameX(){
        return typeNameX;
    }

    public String getTypeNameY(){
        return typeNameY;
    }

    public String getTypeNameZ(){
        return typeNameZ;
    }
}
