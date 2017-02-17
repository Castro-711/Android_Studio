package com.example.castro.calendarevents;

import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;

public class CalendarActivity extends AppCompatActivity {

    private CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendar = (CalendarView) findViewById(R.id.calendarView);

        // take back the last visited date as a string and split it
        // storing it in the dateArray
        // String dateArray[] = getIntent().getExtras().getString("lastVisitedDate").split("/");

        calendar.setOnDateChangeListener(
                new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(
                            CalendarView view, int year, int month, int dayOfMonth) {

                        // months [0 - 11] hence the + 1
                        String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                        Intent i = new Intent(view.getContext(), EventPrompt.class);
                        i.putExtra("currentDate", date);
                        startActivity(i);

                    } // onSelectedDayChange()
                } // OnDateChangeListener()
        );
    }
}
