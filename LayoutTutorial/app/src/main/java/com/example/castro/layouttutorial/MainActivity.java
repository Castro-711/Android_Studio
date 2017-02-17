package com.example.castro.layouttutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    // layout comments

    // by default -> RelativeLayout
    // you can align things relative to the parent
    // relative to other objects you have prev added <generated>

    // wrap_content is relative to text of button for example,
    // it changes with changes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
