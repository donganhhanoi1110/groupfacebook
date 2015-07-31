package org.springframework.social.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemService {

	public boolean checkDate(String dateStart, String dateEnd) {

		Date myDateStart = convertStringToDate(dateStart);
		Date myDateEnd = convertStringToDate(dateEnd);
		if (myDateEnd.getTime() <= (myDateStart.getTime())) {
			return false;
		} else
			return true;
	}

	public String convertStringDateToStringDate(String mydate) {
		Date date = null;
		try {
			SimpleDateFormat formatter;

			formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			date = (Date) formatter.parse(mydate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date.toString();
	}

	public Date convertStringToDate(String mydate) {
		Date date = null;
		try {
			SimpleDateFormat formatter;

			formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			date = (Date) formatter.parse(mydate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public String convertDateToString(Date date) {
		String myDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date);
		return myDate;
	}
	public String convertTimeToString(Date date) {
		String myDate = new SimpleDateFormat("HH:mm:ss").format(date);
		return myDate;
	}
	
	public String formatStringToDDMMYYYYHHMMSS(String datetime) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date date;
		String result="";
		try {
			date = formatter.parse(datetime);
			result=formatter2.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public String convertStringDateFb(String datetime) {
		String result="";
		result = datetime.replace('T', ' ');
		result = result.replace("+0000", "");
		return result;
	}
	public static void main(String[] args) {
		SystemService sysService=new SystemService();
		String date1="10/06/2015 22:30";
		
		Date date=new Date();
		Date systemDate =sysService
				.convertStringToDate(sysService
						.convertDateToString(date));
		//get Schedule Date
		Date scheduleDate = sysService
				.convertStringToDate(date1);
		System.out.println(systemDate);
		System.out.println(scheduleDate);
		System.out.println(systemDate.getTime());
		System.out.println(scheduleDate.getTime());
	}
}
