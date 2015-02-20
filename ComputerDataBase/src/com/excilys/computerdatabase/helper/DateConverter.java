package com.excilys.computerdatabase.helper;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateConverter {

	public static Date stringToDate(String sDate) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date convertedCurrentDate = null;
		try {
			convertedCurrentDate = sdf.parse(sDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return convertedCurrentDate;
	}
}
