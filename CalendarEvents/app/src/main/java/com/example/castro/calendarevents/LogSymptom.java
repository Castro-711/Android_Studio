package com.example.castro.calendarevents;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.InputStream;

public class LogSymptom extends AppCompatActivity {

    private ImageButton camera;
    private Button logSymptom;
    private TextView symptomType;
    private TextView symptomLocation;
    private JsonManip jsonManip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_symptom);

        // init the buttons
        camera = (ImageButton) findViewById(R.id.cameraButton);
        logSymptom = (Button) findViewById(R.id.symptomLogButton);

        // init the textViews
        symptomType = (TextView) findViewById(R.id.symptomWhat);
        symptomLocation = (TextView) findViewById(R.id.symptomWhat);

        // create on click listener for camera button
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dispatchTakePictureIntent();
            } // onClick
        }); // setOnClickListener

        jsonManip = new JsonManip();
        jsonManip.createAndSaveFile("{\n\t[symptoms:{'type':'HIV'");

        Log.i("json_file:", jsonManip.readJsonData());

        logSymptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String what = symptomType.getText().toString();
                String where = symptomLocation.getText().toString();


            } // onClick
        }); // setOnClickListener
    }

    public void logJSON(String symptom, String location){


    }

    // code used to invoke your camera application to take an image
    static final int REQUEST_IMAGE_CAPTURE = 1;

    public void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
}
