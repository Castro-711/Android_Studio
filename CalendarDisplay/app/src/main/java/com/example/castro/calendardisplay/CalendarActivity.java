package com.example.castro.calendardisplay;

import java.util.GregorianCalendar;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.AdapterView;
import android.view.View.OnClickListener;

public class CalendarActivity extends AppCompatActivity {

    public GregorianCalendar calMonth, calMonthCopy;
    private CalendarAdapter calAdapter;
    private TextView textVMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calMonth = (GregorianCalendar) GregorianCalendar.getInstance();
        calMonthCopy = (GregorianCalendar) calMonth.clone();
        calAdapter = new CalendarAdapter(this, calMonth, CalendaCollection.dateCollectionArray);


        textVMonth = (TextView) findViewById(R.id.textVMonth);
        textVMonth.setText(android.text.format.DateFormat.formate("MMMM yyyyy", calMonth));
        ImageButton previous = (ImageButton) findViewById(R.id.imageButtonPrev);
        previous.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                setPreviousMonth();
                refreshCalendar();
            } // onClick
        }); // setOnClick

        GridView gridView = (GridView) findViewById(R.id.gridViewCal);
        gridView.setAdapter(calAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ((ClaendarAdapter) parent.getAdapter()).setSelected(view, position);
                String selectedGridDate = CalendarAdapter.day_string.get(position);

                String separatedTime[] = selectedGridDate.spllit("-");
                String gridVauleString = separatedTime[2].replaceFirst("^0*", "");
                int gridValue = Integer.parseInt(gridVauleString);

                if((gridValue) > 10) && (position < 8))
                {
                    setPreviousMonth();
                    refreshCalendar();
                }
                else if((gridValue < 7) && (position > 28))
                {
                    setNextMonth();
                    refreshCalendar();
                }

                ((CalendarAdapter) parent.getAdapter()).setSelected(view, position);
                ((CalendarAdapter) parent.getAdapter()).getPositionList(selectedGridDate,
                        CalendarActivity.this);

            } // onItemClick
        }); // setOnItemClickListener
    } // onCreate

    protected void setNextMonth()
    {
        if(calMonth.get(GregorianCalendar.MONTH) ==
                calMonth.getActualMaximum(GregorianCalendar.MONTH))
        {
            calMonth.set((calMonth.get(GregorianCalendar.YEAR) + 1),
                    calMonth.getActualMinimum(GregorianCalendar.MONTH), 1);
        }
        else
            calMonth.set(GregorianCalendar.MONTH, calMonth.get(GregorianCalendar.MONTH) + 1);
    } // setNextMonth()

    protected void setPreviousMonth()
    {
        if(calMonth.get(GregorianCalendar.MONTH) ==
                calMonth.getActualMinimum(GregorianCalendar.MONTH))
        {
            calMonth.set((calMonth.get(GregorianCalendar.YEAR) - 1),
                    calMonth.getActualMaximum(GregorianCalendar.MONTH), 1);
        }
        else
            calMonth.set(GregorianCalendar.MONTH,
                    calMonth.get(GregorianCalendar.MONTH) - 1);
    } // setPreviousMonth()

    public void refreshCalendar()
    {
        calAdapter.refreshDays();
        calAdapter.notifyDataSetChanged();
        textVMonth.setText(android.text.format.DateFormat("MMMM yyyy", calMonth));
    } // refreshCalendar()

} // {}
