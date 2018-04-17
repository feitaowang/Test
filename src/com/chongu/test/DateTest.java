package com.chongu.test;

import java.util.Calendar;
import java.util.Date;

public class DateTest {
	public static void main(String[] args){
		Date sdate =  new Date(),edate =new Date();
		
		Calendar sCalendar = Calendar.getInstance() ;
		Calendar eCalendar = Calendar.getInstance();

		sdate = GetDate.getFirstDayOfMonth(new Date());
		edate = GetDate.getLastDayOfMonth(new Date());
		System.out.println("sdate--->"+sdate);
		sCalendar.setTime(new Date());
		eCalendar.setTime(edate);
	}
}
