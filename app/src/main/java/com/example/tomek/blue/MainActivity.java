package com.example.tomek.blue;

import android.content.Context;
import android.location.LocationManager;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends ActionBarActivity {
    private String TAG = "BluetoothConnector";
    BlueController blueController;
    private boolean isTimerStopped = true;
    private Context ctx;

    TextView accelerationName;
    TextView accelerationValue;
    TextView angleName;
    TextView angleValue;
    TextView batteryCurrentName;
    TextView batteryCurrentValue;
    TextView batteryVoltageName;
    TextView batteryVoltageValue;
    TextView chargeName;
    TextView chargeValue;
    TextView enginePowerName;
    TextView enginePowerValue;
    TextView solarVoltageName;
    TextView solarVoltageValue;
    TextView speedName;
    TextView speedValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctx = getBaseContext();
        isTimerStopped = false;

        setupTextViews();
        setupValueNamesInTextViews();

        startTimer();
        //LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        blueController = new BlueController(ctx);
        //TODO dodac wszystkietext view z opisem (warstwa prezentacji)


//        sendButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                blueController.closeDocument();
//            }
//        });

//        getButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //setTextInTextView();
//            }
//        });
    }

    private void setupTextViews(){
        accelerationName = (TextView) findViewById(R.id.acceleration_name);
        accelerationValue = (TextView) findViewById(R.id.acceleration_value);
        angleName = (TextView) findViewById(R.id.angle_name);
        angleValue = (TextView) findViewById(R.id.angle_value);
        batteryCurrentName = (TextView) findViewById(R.id.battery_current_name);
        batteryCurrentValue = (TextView) findViewById(R.id.battery_current_value);
        batteryVoltageName = (TextView) findViewById(R.id.battery_voltage_name);
        batteryVoltageValue = (TextView) findViewById(R.id.battery_voltage_value);
        chargeName = (TextView) findViewById(R.id.battery_charge_name);
        chargeValue = (TextView) findViewById(R.id.battery_charge_value);
        enginePowerName = (TextView) findViewById(R.id.engine_power_name);
        enginePowerValue = (TextView) findViewById(R.id.engine_power_value);
        solarVoltageName = (TextView) findViewById(R.id.solar_voltage_name);
        solarVoltageValue = (TextView) findViewById(R.id.solar_voltage_value);
        speedName = (TextView) findViewById(R.id.speed_name);
        speedValue = (TextView) findViewById(R.id.speed_value);
    }

    private void setupValueNamesInTextViews(){
        accelerationName.setText(BlueAcceleration.getTypeName());
        angleName.setText(BlueAngle.getTypeName());
        batteryCurrentName.setText(BlueBatteryCurrent.getTypeName());
        batteryVoltageName.setText(BlueBatteryVoltage.getTypeName());
        chargeName.setText(BlueCharge.getTypeName());
        enginePowerName.setText(BlueEnginePower.getTypeName());
        solarVoltageName.setText(BlueSolarVoltage.getTypeName());
//        speedName.setText(BlueSpeed.getTypeName());
    }

    private void setValueInTextView(){
        accelerationValue.setText(BlueAcceleration.getInstance().getValueString());
        angleValue.setText(BlueAngle.getInstance().getValueXString());
        batteryCurrentValue.setText(BlueBatteryCurrent.getInstance().getValueString());
        batteryVoltageValue.setText(BlueBatteryVoltage.getInstance().getValueString());
        chargeValue.setText(BlueCharge.getInstance().getValueString());
        enginePowerValue.setText(BlueEnginePower.getInstance().getValueString());
        solarVoltageValue.setText(BlueSolarVoltage.getInstance().getValueString());
//        speedValue.setText(BlueSpeed.getInstance().getValueString());
    }

    private CountDownTimer countDownTimer = new CountDownTimer(10000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            setValueInTextView();
            blueController.saveDataInFile();
            startTimer();
        }
    };

    private void startTimer(){
        if(!isTimerStopped)
            countDownTimer.start();
    }

    public void stopTimer(){
        isTimerStopped = true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        blueController.closeDocument();
        blueController.closeCommunication();
        isTimerStopped = true;
    }
}
