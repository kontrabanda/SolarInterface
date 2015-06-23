package com.example.tomek.blue;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Tomek on 2015-06-21.
 */

//TODO blad przy otwieraniu pliku
public class BlueDocument {
    String TAG = "BluetoothConnector";
    String filename = "file.xml";
    XmlSerializer serializer;
    File file;
    FileOutputStream fos;
    String extension = ".txt";
    String fullFilename;

    BlueDocument() {
        this.serializer = Xml.newSerializer();

        if(isExternalStorageWritable()) {
            getAlbumStorageDir();
            this.setupDocument();
        }
    }

    BlueDocument(String name){
        this.filename = name;
        this.serializer = Xml.newSerializer();

        if(isExternalStorageWritable()) {
            getAlbumStorageDir();
            this.setupDocument();
        }
    }

    BlueDocument(String name, String ext){
        this.filename = name;
        this.extension = ext;
        this.serializer = Xml.newSerializer();

        if(isExternalStorageWritable()) {
            getAlbumStorageDir();
            this.setupDocument();
        }
    }

    private boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            Log.i(TAG, "[BlueDocument]#isExternalStorageReadable: isReadable=true");
            return true;
        }
        Log.i(TAG, "[BlueDocument]#isExternalStorageReadable: isReadable=false");
        return false;
    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            Log.i(TAG, "[BlueDocument]#isExternalStorageReadable: isWritable=true");
            return true;
        }
        Log.i(TAG, "[BlueDocument]#isExternalStorageReadable: isWritable=false");
        return false;
    }


    private void getAlbumStorageDir() {
        this.fullFilename = this.filename + this.extension;
        this.file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), this.fullFilename);

        if (!file.getParentFile().mkdirs()) {
            Log.e(TAG, "[BlueDocument]#getAlbumStorageDir: File not created");
        }
    }

    private void setupDocument(){
        try {
            fos = new FileOutputStream(file);

            Log.i(TAG, file.getPath());
        } catch (FileNotFoundException e) {
            Log.e(TAG, "[BlueDocument]#setupDocument File not found: " + e.getMessage());
        } catch (Exception e){
            Log.e(TAG, "[BlueDocument]#setupDocument Exception: " + e.getMessage());
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
