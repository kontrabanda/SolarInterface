package com.example.tomek.blue;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import android.view.View;

/**
 * Created by Tomek on 2015-06-14.
 */
public class BlueController {
    private BluetoothArduino mBlue = BluetoothArduino.getInstance("HC-05");
    private List<BluetoothValue> valueList = new ArrayList<BluetoothValue>();
    protected String TAG = "BluetoothConnector";
    private Handler handler;
    private boolean Running = true;
    private int number = 0;
    private TextView textView;

    public BlueController(){
        setupValueList();
        //textView = (TextView) findViewById(R.id.tvPD);

        mBlue.Connect();



//        new Thread(runnable).start();
    }


    private void setupValueList(){
        valueList.add(BlueAcceleration.getInstance());
        valueList.add(BlueAngle.getInstance());
        valueList.add(BlueBatteryVoltage.getInstance());
        valueList.add(BlueBatteryCurrent.getInstance());
        valueList.add(BlueSolarVoltage.getInstance());
        valueList.add(BlueEnginePower.getInstance());
    }

    public void getValues(){
        for(BluetoothValue element : valueList){
            element.printValue();
        }
    }

    class TimeEvent extends EventObject {
        public TimeEvent(Object source) {
            super(source);
        }
    }

    public String getAccelerationValue(){
        return Integer.toString(BlueAcceleration.getInstance().getValue());
    }
}
