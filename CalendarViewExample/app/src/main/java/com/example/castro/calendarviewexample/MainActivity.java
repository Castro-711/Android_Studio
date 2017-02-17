package com.example.castro.calendarviewexample;

import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.view.inputmethod.ExtractedText;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Context has to be passed to a calender view constructor,
        it is used in many different views in android dev. It helps
        objects get information on the current state of apps/objects
        in this case context is -> this
         */
        CalendarView mediCalendar = new CalendarView(this);

        mediCalendar.setOnDateChangeListener(
                new OnDateChangeListener(){
                    @Override
                    public void onSelectedDayChange(CalendarView view,
                          int year, int month, int dayOfMonth) {

                        // month is between [0-11]
                        int day = dayOfMonth;
                        currentDate = "" + dayOfMonth + "/" + month + 1;
                    } // onSelectedDateChange
                } // onDateChangeListener
        );

        // Intent currentDate = new Intent(this, ExtractedText);
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast.makeText(context, currentDate, duration).show();

    }
}
