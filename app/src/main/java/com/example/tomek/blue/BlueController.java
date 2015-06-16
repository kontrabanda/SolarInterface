package com.example.tomek.blue;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Tomek on 2015-06-14.
 */
public class BlueController {
    private BluetoothArduino mBlue = BluetoothArduino.getInstance("HC-05");
    private List<BluetoothValue> valueList = new ArrayList<BluetoothValue>();
    protected String TAG = "BluetoothConnector";

    public BlueController(){
        setupValueList();

        mBlue.Connect();
    }

    private void setupValueList(){
        valueList.add(new BlueAcceleration());
        valueList.add(new BlueAngle());
        valueList.add(new BlueBatteryVoltage());
        valueList.add(new BlueBatteryCurrent());
        valueList.add(new BlueSolarVoltage());
        valueList.add(new BlueEnginePower());
    }

    public void getValues(){
        for(BluetoothValue element : valueList){
            element.printValue();
        }
    }
}
