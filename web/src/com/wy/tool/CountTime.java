package com.wy.tool;
import java.util.Date;
import java.text.DateFormat;
import java.util.GregorianCalendar;
public class CountTime {
	public String currentlyTime() {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
		return dateFormat.format(date);
	}
}
