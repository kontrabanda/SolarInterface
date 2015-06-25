package com.example.tomek.blue;

/**
 * Created by Tomek on 2015-06-23.
 */

public class BlueCharge extends BluetoothValue {
    volatile private BlueBatteryVoltage blueBatteryVoltage;
    volatile private BlueBatteryCurrent blueBatteryCurrent;
    private double chargeValue = 0;
    private int maxValue = 100;
    private int minValue = 0;
    private static BlueCharge __charge = null;
    private final static String typeName = "Naladowanie baterii";

    public static BlueCharge getInstance(){
        return __charge == null ? new BlueCharge() : __charge;
    }

    private BlueCharge(){
        blueBatteryVoltage = BlueBatteryVoltage.getInstance();
        blueBatteryCurrent = BlueBatteryCurrent.getInstance();
    }

    private void calculateValue(){

        double batteryVoltage = blueBatteryVoltage.getValue();
        double batteryCurrent = blueBatteryCurrent.getValue();

        if(batteryCurrent != -1 && batteryVoltage != -1){
            //TODO stworzyc funkcje do obliczenia wartosci aktualnej naladowania baterii
        }
    }

    private void setValue(double value){
        this.chargeValue = value;
        this.valueChanged = true;
    }

    public double getValue(){
        if(valueChanged){
            this.valueChanged = false;
            return chargeValue;
        } else {
            return -1;
        }
    }

    public String getValueString(){
        String value = "";
        try{
            value = Double.toString(this.chargeValue);
        } catch (Exception e) {
            value = "error";
        }

        return value;
    }

    public String getTypeName(){
        return typeName;
    }
}