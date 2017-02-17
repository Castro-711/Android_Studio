package com.androidfromhome.calendar.util;

import java.util.ArrayList;

// in here is where we will set all of the correct attributes
// for symptoms, reminders, appointments etc

public class CalendarCollection
{
	public String date = "";
	public String event_message = "";

	// this is static and belongs to the class but I am not
	// sure if it should be static, would that not mean it is
	// shared across many different peoples calendars ..?
	public static ArrayList<CalendarCollection> date_collection_arr;

	// two args constructor
	public CalendarCollection(String date, String event_message)
	{
		this.date = date;
		this.event_message = event_message;
	} // constructor

}
