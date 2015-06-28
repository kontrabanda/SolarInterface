package com.example.tomek.blue;

import android.content.Context;
import android.location.LocationManager;
import android.nfc.Tag;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
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
    private Context context;
    private BlueDocument documentWriter;
    private LocationManager locationManager;

    private SimpleDateFormat timeFormat;
    private SimpleDateFormat dateFormat;

    public BlueController(Context c){
        setupValueList();
        this.context = c;
        //this.locationManager = locationManager;
        //textView = (TextView) findViewById(R.id.tvPD);
        this.dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        dateFormat.setLenient(false);
        this.timeFormat = new SimpleDateFormat("HH:mm:ss");
        timeFormat.setLenient(false);

        mBlue.Connect();
        documentWriter = new BlueDocument("Pomiary_" + getCurrentDate(), ".txt");

        getCurrentDate();
    }

    private String getCurrentDate(){
        Date today = new Date();
        String todayString = dateFormat.format(today);
        return todayString;
    }

    private String getCurrentTime(){
        Date now = new Date();
        String nowString = timeFormat.format(now);
        return nowString;
    }

    private ArrayList<BlueDataStruct> getValuesIntoList(){
        ArrayList<BlueDataStruct> valuesList = new ArrayList<BlueDataStruct>();

        BlueAcceleration blueAcceleration = BlueAcceleration.getInstance();
        BlueAngle blueAngle = BlueAngle.getInstance();
        BlueBatteryCurrent blueBatteryCurrent = BlueBatteryCurrent.getInstance();
        BlueBatteryVoltage blueBatteryVoltage = BlueBatteryVoltage.getInstance();
        BlueSolarVoltage blueSolarVoltage = BlueSolarVoltage.getInstance();
        BlueEnginePower blueEnginePower = BlueEnginePower.getInstance();
        //BlueSpeed blueSpeed = BlueSpeed.getInstance(locationManager);
        BlueCharge blueCharge = BlueCharge.getInstance();

        valuesList.add(new BlueDataStruct(blueAcceleration.getTypeName(), blueAcceleration.getValueString()));
        valuesList.add(new BlueDataStruct(blueAngle.getTypeNameX(), blueAngle.getValueXString()));
        valuesList.add(new BlueDataStruct(blueAngle.getTypeNameY(), blueAngle.getValueXString()));
        valuesList.add(new BlueDataStruct(blueAngle.getTypeNameZ(), blueAngle.getValueZString()));
        valuesList.add(new BlueDataStruct(blueBatteryCurrent.getTypeName(), blueBatteryCurrent.getValueString()));
        valuesList.add(new BlueDataStruct(blueBatteryVoltage.getTypeName(), blueBatteryVoltage.getValueString()));
        valuesList.add(new BlueDataStruct(blueEnginePower.getTypeName(), blueEnginePower.getValueString()));
        valuesList.add(new BlueDataStruct(blueSolarVoltage.getTypeName(), blueSolarVoltage.getValueString()));
        //valuesList.add(new BlueDataStruct(blueSpeed.getTypeName(), blueSpeed.getValueString()));
        valuesList.add(new BlueDataStruct(blueCharge.getTypeName(), blueCharge.getValueString()));

        return valuesList;
    }

    private void setupValueList(){
        valueList.add(BlueAcceleration.getInstance());
        valueList.add(BlueAngle.getInstance());
        valueList.add(BlueBatteryVoltage.getInstance());
        valueList.add(BlueBatteryCurrent.getInstance());
        valueList.add(BlueSolarVoltage.getInstance());
        valueList.add(BlueEnginePower.getInstance());
        //valueList.add(BlueSpeed.getInstance(locationManager));
        valueList.add(BlueCharge.getInstance());
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

    public void saveDataInFile(){
        documentWriter.addTag(getCurrentTime(), getValuesIntoList());
//        Log.i(TAG, getCurrentTime());
//        getValuesIntoList();
    }

    public void closeDocument(){
        documentWriter.closeDocument();
    }

    public void closeCommunication() {
        mBlue.closeCommunication();
    }
}
