package com.example.tomek.blue;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Tomek on 2015-06-23.
 */
//TODO przetestowac BlueSpeed czy dzia³a poprawnie
public class BlueSpeed extends BluetoothValue{
    volatile private double speedValue = 0;

    private LocationManager locationManager;
    private final static String typeName = "Predkosc";
    private static BlueSpeed __speed = null;

    public static BlueSpeed getInstance(LocationManager locationManager){
        return __speed == null ? new BlueSpeed(locationManager) : __speed;
    }

    private BlueSpeed(LocationManager locationManager){
        this.locationManager = locationManager;

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
                0, locationListener);
    }

    LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            location.getLatitude();
            Log.i("LocationB", "Location" + location.getAccuracy());
            Log.i("LocationB", "Speed " + location.getSpeed());
//                Toast.makeText(context, "Current speed:" + location.getSpeed(),
//                        Toast.LENGTH_SHORT).show();
            setValue(location.getSpeed());
        }

        public void onStatusChanged(String provider, int status,
                                    Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }
    };

    private void setValue(double value){
        this.valueChanged = true;
        this.speedValue = value;
    }

    public double getValue(){
        if(valueChanged){
            this.valueChanged = false;
            return this.speedValue;
        } else {
            return -1;
        }
    }

    public String getValueString(){
        String value = "";
        try{
            value = Double.toString(this.speedValue);
        } catch (Exception e) {
            value = "error";
        }

        return value;
    }

    public static String getTypeName(){
        return typeName;
    }
}
