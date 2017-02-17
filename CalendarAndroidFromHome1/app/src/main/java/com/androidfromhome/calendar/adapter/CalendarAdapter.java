package com.androidfromhome.calendar.adapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidfromhome.calendar.CalenderActivity;
import com.androidfromhome.calendar.Event.TodaysEvents;
import com.androidfromhome.calendar.R;
import com.androidfromhome.calendar.util.CalendarCollection;

public class CalendarAdapter extends BaseAdapter {
	private Context context;

	private java.util.Calendar month;
	public GregorianCalendar pmonth;

	// calendar instance for previous month for getting complete view
	public GregorianCalendar pmonthmaxset;
	private GregorianCalendar selectedDate;
	int firstDay;
	int maxWeeknumber;
	int maxP;
	int calMaxP;
	int lastWeekDay;
	int leftDays;
	int mnthlength;
	String itemvalue, curentDateString;
	DateFormat df;

	private ArrayList<String> items;

	// holds a list of dates in the latest calendar view
	public static List<String> day_string;
	private View previousView;
	public ArrayList<CalendarCollection>  date_collection_arr;

	public CalendarAdapter(Context context, GregorianCalendar monthCalendar,
						   ArrayList<CalendarCollection> date_collection_arr)
	{
		this.date_collection_arr = date_collection_arr;
		CalendarAdapter.day_string = new ArrayList<String>();
		Locale.setDefault(Locale.ENGLISH);
		month = monthCalendar;
		selectedDate = (GregorianCalendar) monthCalendar.clone();
		this.context = context;
		month.set(GregorianCalendar.DAY_OF_MONTH, 1);
		
		this.items = new ArrayList<String>();
		df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		curentDateString = df.format(selectedDate.getTime());
		refreshDays();
		
	} // CalendarAdapter constructor

	public void setItems(ArrayList<String> items)
	{
		for (int i = 0; i != items.size(); i++)
		{
			if (items.get(i).length() == 1)
				items.set(i, "0" + items.get(i));

		} // for
		this.items = items;
	} // setItems

	public int getCount() {
		return day_string.size();
	}

	public Object getItem(int position) {
		return day_string.get(position);
	}

	public long getItemId(int position) {
		return 0;
	}

	// create a new view for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View v = separatingGetView(position, convertView, parent);

		return v;
	} // getView()

	public View separatingGetView(int position, View convertView, ViewGroup parent)
	{
		View v = convertView;
		TextView dayView;
		if (convertView == null)
		{ // if it's not recycled, initialize some attributes
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.cal_item, null);
		} // if

		dayView = (TextView) v.findViewById(R.id.date);
		String[] separatedTime = day_string.get(position).split("-");

		String gridvalue = separatedTime[2].replaceFirst("^0*", "");

		// section colours in the individual dates
		// setting prev, next months dates to grey
		if ((Integer.parseInt(gridvalue) > 1) && (position < firstDay))
		{
			dayView.setTextColor(Color.GRAY);
			dayView.setClickable(false);
			dayView.setFocusable(false);
		} else if ((Integer.parseInt(gridvalue) < 7) && (position > 28))
		{
			dayView.setTextColor(Color.GRAY);
			dayView.setClickable(false);
			dayView.setFocusable(false);
		}
		else
			// setting current month's days in white.
			dayView.setTextColor(Color.WHITE);


		if (day_string.get(position).equals(curentDateString))
			v.setBackgroundColor(Color.DKGRAY);
		else
			v.setBackgroundColor(Color.parseColor("#343434"));

		dayView.setText(gridvalue);

		// create date string for comparison
		String date = day_string.get(position);

		if (date.length() == 1)
			date = "0" + date;

		String monthStr = "" + (month.get(GregorianCalendar.MONTH) + 1);
		if (monthStr.length() == 1) {
			monthStr = "0" + monthStr;
		}

		// show icon if date is not empty and it exists in the items array
//		ImageView iw = (ImageView) v.findViewById(R.id.date_icon);
//		if (date.length() > 0 && items != null && items.contains(date)) {
//			iw.setVisibility(View.VISIBLE);
//		} else {
//			iw.setVisibility(View.GONE);
//		}

		Log.i("Position: ", " " + position);

		setEventView(v, position, dayView);

		return v;
	}

	public View setSelected(View view, int pos)
	{
		TextView dayView = (TextView) view.findViewById(R.id.date);

		int numberOfEvents = CalendarCollection.date_collection_arr.size();
		ArrayList<CalendarCollection> eventDates = CalendarCollection.date_collection_arr;

//		System.out.println("Events at " + i + " " + eventDates.get(i).date);
//		Log.i("day_string.get(pos): ", "" + day_string.get(pos));
		// checks that the user has viewed a previous cal date
//		if (previousView != null)
//		{
//			previousView.setBackgroundColor(Color.parseColor("#ffffff"));
//			Log.i("Here here: ", "I am in the previousView != null\n" +
//					"and the pos = " + pos);
//		}



		// changes colour of the selected date
		//view.setBackgroundColor(Color.LTGRAY);

		// day_string is a comma separated list of all the dates on show

		// the number of events registered
		int len = day_string.size();

		Log.i("len: ", "" + len);
		Log.i("day_string: ", "" + day_string);
		Log.i("currentDateString: ", "" + curentDateString);

		// currentDateString is very self explanatory
		// it holds the current date on which the user is using the app

		if (len > pos)
			if (!day_string.get(pos).equals(curentDateString) &&
					!eventDates.contains(day_string))
				previousView = view;

		return view;
	} // setSelectedView()

	public void refreshDays()
	{
		// clear items
		items.clear();
		day_string.clear();
		Locale.setDefault(Locale.US);
		pmonth = (GregorianCalendar) month.clone();

		// month start day. ie; sun, mon, etc
		firstDay = month.get(GregorianCalendar.DAY_OF_WEEK);

		// finding number of weeks in current month.
		maxWeeknumber = month.getActualMaximum(GregorianCalendar.WEEK_OF_MONTH);

		// allocating maximum row number for the gridview.
		mnthlength = maxWeeknumber * 7;
		maxP = getMaxP(); // previous month maximum day 31,30....
		calMaxP = maxP - (firstDay - 1);// calendar offday starting 24,25 ...

		// Calendar instance for getting a complete gridview including the three
		// month's (previous,current,next) dates.
		pmonthmaxset = (GregorianCalendar) pmonth.clone();

		// setting the start date as previous month's required date.
		pmonthmaxset.set(GregorianCalendar.DAY_OF_MONTH, calMaxP + 1);

		// filling calendar gridview.
		for (int n = 0; n < mnthlength; n++)
		{
			itemvalue = df.format(pmonthmaxset.getTime());
			pmonthmaxset.add(GregorianCalendar.DATE, 1);
			day_string.add(itemvalue);
		} // for
	} // refreshDays()

	private int getMaxP()
	{
		int maxP;

		if (month.get(GregorianCalendar.MONTH) ==
				month.getActualMinimum(GregorianCalendar.MONTH))
		{
			pmonth.set((month.get(GregorianCalendar.YEAR) - 1),
					month.getActualMaximum(GregorianCalendar.MONTH), 1);
		}
		else
		{
			pmonth.set(GregorianCalendar.MONTH,
					month.get(GregorianCalendar.MONTH) - 1);
		}

		maxP = pmonth.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

		return maxP;
	}
	
	public void setEventView(View v,int pos,TextView txt)
	{
		// at the moment this is given a null pointer exception
		// for now I will just substitute change it with an if else
		int len = CalendarCollection.date_collection_arr.size();

		// cycles through the date_collection_array
		for (int i = 0; i < len; i++)
		{
			CalendarCollection cal_obj = CalendarCollection.date_collection_arr.get(i);
			String date = cal_obj.date;
			int len1 = day_string.size();

			// this checks that the event is ahead of current date
			if (len1 > pos)
			{
				// checks if there is an event and colours it appropriately
				if (day_string.get(pos).equals(date))
				{
					// in here is where it populates the existing events
					// scheduled by the user for symptoms, reminders etc

					// depending on whether it is a symptom or reminder
					// we will be changing the colour to suit
					// at the moment we will leave it at that

					// I think the setBackgroundColor is redundant here
					// commenting it out for now..
					// v.setBackgroundColor(Color.parseColor("#ccffcc"));

					// just to check if the position is what I thought
					Log.i("day string: ", "" + day_string.get(pos).charAt(9));

					if(day_string.get(pos).charAt(8) == '1')
						v.setBackgroundResource(R.drawable.green_rounded_calendar_item);
					else if(day_string.get(pos).charAt(9) == '3')
						v.setBackgroundResource(R.drawable.red_rounded_calendar_item);
					else
						v.setBackgroundResource(R.drawable.blue_rounded_calendar_item);

					txt.setTextColor(Color.WHITE);
				} // if
			} // if len1
		} // for
	} // setEventView
	
	
	public void getPositionList(String date,final Activity act)
	{
		// at the moment this is given a null pointer exception
		// for now I will just substitute change it with an if else
		int len;
		if(CalendarCollection.date_collection_arr == null)
			len = 0;
		else
			len = CalendarCollection.date_collection_arr.size();

		for (int i = 0; i < len; i++)
		{
			CalendarCollection cal_collection = CalendarCollection.date_collection_arr.get(i);
			String event_date = cal_collection.date;

			Log.i("event date -----> ", " " + event_date + " here is date: " + date);

			// this is activated when the selected date has an event
			if (date.equals(event_date))
			{
				// hiding toast for now

				// this is where we will call the activity for to show
				// what events are already present on this day

				break;
			} // if
		} // for i
	} // getPositionList()

} // {}