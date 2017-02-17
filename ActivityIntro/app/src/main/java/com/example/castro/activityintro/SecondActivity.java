package com.example.castro.activityintro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    // m -> member
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mButton = (Button) findViewById(R.id.button2);

        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                Intent i = new Intent(view.getContext(), FirstActivity.class);
                startActivity(i);
            }  // onClick
        });
    }
}
