package com.androidfromhome.calendar;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.androidfromhome.calendar.adapter.AndroidListAdapter;
import com.androidfromhome.calendar.adapter.CalendarAdapter;
import com.androidfromhome.calendar.symptom.SymptomActivity;
import com.androidfromhome.calendar.util.CalendarCollection;

public class CalenderActivity extends Activity {
	public GregorianCalendar cal_month, cal_month_copy;
	private CalendarAdapter cal_adapter;
	private TextView tv_month;

	// this is from the member section of the ListViewActivity
	// I am trying to migrate them over as we don't need that
	// class and hope to extract all that we do need from it
	private ListView lv_android;
	private AndroidListAdapter list_adapter;
	private Button btn_calender;
	private CalendarCollection calCollection;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calender);

		// ensure the calCollection is not null
		setCalCollection();

		cal_month = (GregorianCalendar) GregorianCalendar.getInstance();
		cal_month_copy = (GregorianCalendar) cal_month.clone();
		cal_adapter = new CalendarAdapter(this, cal_month, CalendarCollection.date_collection_arr);

		tv_month = (TextView) findViewById(R.id.tv_month);
		tv_month.setText(android.text.format.DateFormat.format("MMMM yyyy", cal_month));

		ImageButton previous = (ImageButton) findViewById(R.id.ib_prev);

		previous.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setPreviousMonth();
				refreshCalendar();
			}
		});

		ImageButton next = (ImageButton) findViewById(R.id.Ib_next);
		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setNextMonth();
				refreshCalendar();

			}
		});

		GridView gridview = (GridView) findViewById(R.id.gv_calendar);
		gridview.setAdapter(cal_adapter);
		gridview.setOnItemClickListener(new OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
			
				((CalendarAdapter) parent.getAdapter()).setSelected(v,position);
				String selectedGridDate = CalendarAdapter.day_string.get(position);

				String[] separatedTime = selectedGridDate.split("-");
				String gridvalueString = separatedTime[2].replaceFirst("^0*","");
				int gridvalue = Integer.parseInt(gridvalueString);

				if ((gridvalue > 10) && (position < 8))
				{
					setPreviousMonth();
					refreshCalendar();
				}
				else if ((gridvalue < 7) && (position > 28))
				{
					setNextMonth();
					refreshCalendar();
				}

				((CalendarAdapter) parent.getAdapter()).setSelected(v, position);

				((CalendarAdapter) parent.getAdapter()).getPositionList(
                        selectedGridDate, CalenderActivity.this);


				if(CalendarCollection.date_collection_arr.contains(selectedGridDate))
				{
					Log.i("in calcollection---> ", " horray");
				}
				// create the new activity and navigate to it
				Intent i = new Intent(CalenderActivity.this, SymptomActivity.class);
				startActivity(i);

			}
			
		}); // setOnclickItemListener
	} // onCreate()

	@Override
	public void onResume()
	{
		super.onResume();
		// this line below prevents the calendar from displaying the new months
		// when the user changes to previous or next
		// cal_adapter = new CalendarAdapter(this, cal_month, CalendarCollection.date_collection_arr);
	}
	
	protected void setNextMonth() {
		if (cal_month.get(GregorianCalendar.MONTH) ==
				cal_month.getActualMaximum(GregorianCalendar.MONTH)) {
			cal_month.set((cal_month.get(GregorianCalendar.YEAR) + 1),
					cal_month.getActualMinimum(GregorianCalendar.MONTH), 1);
		} else {
			cal_month.set(GregorianCalendar.MONTH,
					cal_month.get(GregorianCalendar.MONTH) + 1);
		}

	} // setNextMonth()

	protected void setPreviousMonth() {
		if (cal_month.get(GregorianCalendar.MONTH) ==
				cal_month.getActualMinimum(GregorianCalendar.MONTH))
		{
			cal_month.set((cal_month.get(GregorianCalendar.YEAR) - 1),
					cal_month.getActualMaximum(GregorianCalendar.MONTH), 1);
		}
		else
		{
			cal_month.set(GregorianCalendar.MONTH,
					cal_month.get(GregorianCalendar.MONTH) - 1);
		}

	} // setPreviousMonth()

	public void refreshCalendar()
	{
		cal_adapter.refreshDays();
		cal_adapter.notifyDataSetChanged();
		tv_month.setText(android.text.format.DateFormat.format("MMMM yyyy", cal_month));
	} // refreshCalendar()

	// set up the collection array that populates the calendar with events
	public void setCalCollection()
	{
		// this is from the onCreate from the ListViewActivity
		// i have deleted most of the entries but they all follow
		// the exact same way to add it
		calCollection.date_collection_arr = new ArrayList<CalendarCollection>();
		calCollection.date_collection_arr.add(new CalendarCollection("2016-11-03","Kyran's Birthday"));
		calCollection.date_collection_arr.add(new CalendarCollection("2016-11-07","My Birthday"));
		calCollection.date_collection_arr.add(new CalendarCollection("2016-11-13","Danielle's Birthday"));
		// calCollection.date_collection_arr.add(new CalendarCollection("2016-12-23", "Xmas holidays"));
	}
}
