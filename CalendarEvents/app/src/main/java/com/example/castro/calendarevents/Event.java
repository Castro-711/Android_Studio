package com.example.castro.calendarevents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Event extends AppCompatActivity {

    private TextView displayDate;
    private Button backToCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        String curDate = getIntent().getExtras().getString("currentDate");

        displayDate = (TextView) findViewById(R.id.eventDate);
        backToCalendar = (Button) findViewById(R.id.button);

        displayDate.setText(curDate);

        backToCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent(context, class)
                Intent i = new Intent(view.getContext(), CalendarActivity.class);
                i.putExtra("lastVisitedDate", displayDate.getText());
                startActivity(i);
            }
        });
    }
}
