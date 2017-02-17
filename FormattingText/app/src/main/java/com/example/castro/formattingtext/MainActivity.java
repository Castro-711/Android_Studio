package com.example.castro.formattingtext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // this code will run when the app runs

    // ************* USING BUTTONS **************
    // create a function to act when the button is pressed
    // we need a view in the params, as we will pass it the button
    public void clickFunction(View view){

        // Log. i, d, e ...
        // i -> information
        // Log.i(title, log content);
        Log.i("Info", "Button Tapped!");

        // simplest way to get this method to be called is to head to
        // activity_main.xml and go to onclick and add the function name
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ************* FORMATTING TEXT **************
        // layout margin
        // uses dp -> density independent pixel, roughly 1/160th per inch

        // centreInParent
        // allows to centre horizontally and vertically.

        // style
        // contains loads of different styles, find the one you like

        // background
        // this is the background colour but there are afew different methods
        // Project, System, Color Picker

        // padding
        // this is within the item itself where as margin is not

        // text size
        // it is measured in sp instead of dp.
        // sp will scale up for visually impaired users etc
        // only use sp for text size
    }
}
