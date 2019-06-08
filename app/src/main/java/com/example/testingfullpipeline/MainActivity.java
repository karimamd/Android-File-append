package com.example.testingfullpipeline;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private File file;
    private FileOutputStream fos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OutputStreamWriter outputStreamWriter = null;
        file = new File(Environment.getExternalStorageDirectory(),"config.txt");
        try {
            fos = new FileOutputStream(file, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        try {
            outputStreamWriter = new OutputStreamWriter(this.openFileOutput("config.txt", Context.MODE_PRIVATE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //outputStreamWriter = new OutputStreamWriter(fos);
        writeToFile("salamo 3aleeko 1"," salamo 3aleeko 1.2", this, outputStreamWriter);
        //outputStreamWriter = new OutputStreamWriter(fos);
        try {
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStreamWriter = new OutputStreamWriter(this.openFileOutput("config.txt", Context.MODE_PRIVATE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writeToFile("salamo 2 ", " salamo 3aleeko 2.2",this, outputStreamWriter);
        try {
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String salam= readFromFile(this);
        Log.i("EngZoz", salam);

    }
    private void writeToFile(String data,String data2,Context context,OutputStreamWriter outputStreamWriter ) {

        try {
            //file = new File(Environment.getExternalStorageDirectory(),"config.txt");
            //FileOutputStream fos = new FileOutputStream(file, true);

            //OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);

            outputStreamWriter.append(data);
            outputStreamWriter.append(data2);
            //outputStreamWriter.flush();


        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("config.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                    //Log.i("bahaba2", receiveString);
                }


                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

}
