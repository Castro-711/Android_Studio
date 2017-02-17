package com.example.castro.deanodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView myView;
    private Button changeButton;
    private TextView done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myView = (TextView) findViewById(R.id.mainTextView);
        changeButton = (Button) findViewById(R.id.mainButton);
        done=(TextView)findViewById(R.id.bitch);
        changeButton.setOnClickListener(new View.OnClickListener() {

            int counter = 0;
            @Override
            public void onClick(View v) {

                String text;
                if(counter % 2 == 0)
                    text = "Its Friday ..!";
                else
                    text = "Happy Days..!";

                    done.setText(counter+"");

                myView.setText(text);
                counter++;
            }
        });
    }


}
