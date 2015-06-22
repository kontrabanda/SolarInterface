package com.example.tomek.blue;

import android.content.Context;
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

    private SimpleDateFormat timeFormat;
    private SimpleDateFormat dateFormat;

    public BlueController(Context c){
        setupValueList();
        this.context = c;
        //textView = (TextView) findViewById(R.id.tvPD);

//        mBlue.Connect();

        //TODO funkcje do dodawania wczytywanych danych do pliku (zapisywanie przez timer)
        //zapisywanie ma sie odbywac do osobnych plikow (data powinna byc w nazwie aby zapewnic
        //unikalnosc

//        documentWriter = new BlueDocument("Pomiary " + getCurrentDate(), context);

        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        this.timeFormat = new SimpleDateFormat("HH:mm:ss");
        timeFormat.setLenient(false);

        Log.i(TAG, getCurrentTime());

//        new Thread(runnable).start();
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

        valuesList.add(new BlueDataStruct(blueAcceleration.getTypeName(), blueAcceleration.getValueString()));
        valuesList.add(new BlueDataStruct(blueAngle.getTypeNameX(), blueAngle.getValueXString()));
        valuesList.add(new BlueDataStruct(blueAngle.getTypeNameY(), blueAngle.getValueXString()));
        valuesList.add(new BlueDataStruct(blueAngle.getTypeNameZ(), blueAngle.getValueZString()));
        valuesList.add(new BlueDataStruct(blueBatteryCurrent.getTypeName(), blueBatteryCurrent.getValueString()));
        valuesList.add(new BlueDataStruct(blueBatteryVoltage.getTypeName(), blueBatteryVoltage.getValueString()));
        valuesList.add(new BlueDataStruct(blueEnginePower.getTypeName(), blueEnginePower.getValueString()));
        valuesList.add(new BlueDataStruct(blueSolarVoltage.getTypeName(), blueSolarVoltage.getValueString()));

        return valuesList;
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

    public void saveDataInFile(){
        documentWriter.addTag(getCurrentTime(), getValuesIntoList());
    }

    public void closeDocument(){
        documentWriter.closeDocument();
    }
}
