package com.example.tomek.blue;

import android.content.Context;
import android.util.Log;

import org.xmlpull.v1.XmlSerializer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Tomek on 2015-06-21.
 */

//TODO przetestowac cala klase zapisu
public class BlueDocument {
    String TAG = "BluetoothConnector";
    String filename = "file.xml";
    Context context;
    XmlSerializer serializer;
    FileOutputStream fos;

    BlueDocument(Context c) {
        this.context = c;

        this.setupDocument();
    }

    BlueDocument(String name, Context c){
        this.filename = name;
        this.context = c;

        this.setupDocument();
    }

    private void setupDocument(){
        try {
            fos = context.openFileOutput(filename, Context.MODE_APPEND);
        } catch (FileNotFoundException e) {
            Log.e(TAG, "[BlueDocument]#setupDocument File not found: " + e.getMessage());
        }

        try{
            serializer.setOutput(fos, "UTF-8");
            serializer.startDocument(null, Boolean.valueOf(true));
            serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);

            serializer.startTag(null, "root");
        } catch (IOException e) {
            Log.e(TAG, "[BlueDocument]#setupDocument IOException: " + e.getMessage());
        }
    }

    public void addTag(String date, ArrayList<BlueDataStruct> inf){
        try{
            serializer.startTag(null, "record");

            serializer.startTag(null, "date");
            serializer.text(date);
            serializer.endTag(null, "date");

            serializer.startTag(null, "information");

            for(BlueDataStruct element : inf){
                serializer.startTag(null, "type");
                serializer.text(element.type);
                serializer.endTag(null, "type");

                serializer.startTag(null, "value");
                serializer.text(element.value);
                serializer.endTag(null, "value");
            }

            serializer.endTag(null, "information");

            serializer.endTag(null, "record");
        } catch (IOException e) {
            Log.e(TAG, "[BlueDocument]#addTag IOException: " + e.getMessage());
        }
    }

    public void closeDocument(){
        try{
            serializer.endDocument();
            serializer.flush();

            fos.close();

        } catch (IOException e) {
            Log.e(TAG, "[BlueDocument]#closeDocument IOException: " + e.getMessage());
        }
    }
}
