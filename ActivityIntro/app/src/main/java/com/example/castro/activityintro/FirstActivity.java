package com.example.castro.activityintro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    private Button mButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        mButton = (Button) findViewById(R.id.button);

        // intents are used to transition from one activity to the next
        // you can use an setOnClickListener() and within that use the
        // onClick(View view) method

        mButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                // this is a particular intent as we give it a specific task
                // must also add to android manifest to reference a new activity
                Intent i = new Intent(view.getContext(), SecondActivity.class);
                startActivity(i);
            }
        });
    }


}
