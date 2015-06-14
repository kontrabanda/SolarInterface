package com.example.tomek.blue;

import android.util.Log;

/**
 * Created by Tomek on 2015-06-14.
 */
class AdditionalException extends Exception {
    private String TAG = "BluetoothConnector";
    private String msg;

    public AdditionalException(String msg){
        super(msg);
        this.msg = msg;
    }

    public void sendLog(String description){
        Log.e(TAG, description + ": " + this.msg);
    }
}