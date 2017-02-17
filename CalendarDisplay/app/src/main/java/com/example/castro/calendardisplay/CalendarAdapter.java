package com.example.castro.calendardisplay;

import android.content.Context;
import android.view.View;
import android.widget.BaseAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class CalendarAdapter extends BaseAdapter {

    private Context context;
    private Calendar month;
    public GregorianCalendar prevMonth;
    public GregorianCalendar prevMonthMaxSet;
    private GregorianCalendar selectedDate;
    int firstDay;
    int maxWeekNumber;
    int maxP;
    int calMaxP;
    int lastWeekDay;
    int leftDays;
    int monthLength;
    String itemValue, currentDateString;
    DateFormat dateFormat;

    private ArrayList<String> items;
    public static List<String> dayString;
    private View previousView;
    private ArrayList<CalendarCollection> dateCollectionArray;

    public CalendarAdapter(Context context, GregorianCalendar monthCalendar,
                           ArrayList<CalendarCollection> dateCollectionArray)
    {
        this.dateCollectionArray = dateCollectionArray;
        CalendarAdapter.dayString = new ArrayList<String>();
        Locale.setDefault(Locale.ENGLISH);
        month = monthCalendar;
        selectedDate = (GregorianCalendar) monthCalendar.clone();
        this.context = context;
        month.set(GregorianCalendar.DAY_OF_MONTH, 1);

        this.items = new ArrayList<String>();
        dateFormat = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);
        currentDateString = dateFormat.format(selectedDate.getTime());
        refreshDays();
    }

}
