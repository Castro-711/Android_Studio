package com.example.castro.calendarevents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by castro on 19/10/16.
 */

public class JsonManip
{
    public void createAndSaveFile(String jsonResponse)
    {
        try {
            FileWriter file = new FileWriter("/json_files/local_symptoms.json");
            file.write(jsonResponse);
            file.flush(); // flushes any output bytes in the buffer
            file.close(); // close the stream
        } catch (IOException e) { e.printStackTrace(); }
    } // createAndSaveFile

    public String readJsonData()
    {
        String response = "";

        File file = new File("/json_files/local_symtpoms.json");
        try
        {
            FileInputStream inStream = new FileInputStream(file);
            int size = inStream.available(); // returns number of available
            // bytes that can be read without blocking the next method call
            byte[] buffer = new byte[size];
            inStream.read(buffer);
            inStream.close();
            response = new String(buffer);
        }
        catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }

        return response;
    } // readJsonData


}
