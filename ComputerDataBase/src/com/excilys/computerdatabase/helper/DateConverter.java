package com.excilys.computerdatabase.helper;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateConverter {
	/*
	 * permet de convertir une chaine de caractère au format "yyyy-MM-dd" en date
	 */
	public static Date stringToDate(String sDate) {
		Date convertedCurrentDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(sDate == null)
		{
			sDate = "0000-00-00";
		}
		if (!sDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
			sDate = "0000-00-00";
		}
		try {
			convertedCurrentDate = sdf.parse(sDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return convertedCurrentDate;
	}
}
