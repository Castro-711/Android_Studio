package com.example.castro.calendarevents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EventPrompt extends AppCompatActivity {

    private Button reminderButton;
    private Button symptomButton;
    private TextView chosenDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_prompt);

        // instantiate the buttons from this activity
        reminderButton = (Button) findViewById(R.id.reminderButton);
        symptomButton = (Button) findViewById(R.id.symptomButton);

        // instantiate the text view to display the date
        chosenDate = (TextView) findViewById(R.id.chosenDate);
        String displayDate = getIntent().getExtras().getString("currentDate");

        chosenDate.setText(displayDate);

        // add the onClickListeners and the desired functionality to go with it
        reminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(view.getContext(), SetReminder.class);
                startActivity(i);
            } // onClick
        }); // setOnClickListener

        symptomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(view.getContext(), LogSymptom.class);
                startActivity(i);
            }
        });

    } // onCreate
}
