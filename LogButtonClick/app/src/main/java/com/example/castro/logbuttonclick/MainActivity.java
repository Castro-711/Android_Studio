package com.example.castro.logbuttonclick;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // this project is the challenge to create a button
        // call it Click Me and have it log "Hello " + your name

    }

    // create my clickFunction to be called when button is clicked
    // it must be passed a view as the button is a view
    public void clickFunction(View view){

        // Log.i(title, textToLog);
        Log.i("Info", "Hello Eric");
    }

}
