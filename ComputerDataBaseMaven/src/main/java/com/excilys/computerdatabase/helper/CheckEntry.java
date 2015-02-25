package com.excilys.computerdatabase.helper;


public class CheckEntry {
	/**
	 * Check if the param has an id format
	 * @param str
	 * @return boolean
	 */
	public static boolean checkIsId(String str) {
		if (!str.equals("") && !str.equals("0") && str.matches("[0-9]+")) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Check if the param is a good date
	 * @param str
	 * @return boolean
	 */
	public static boolean checkIsDate(String str) {
		if (str != null) {
			if (str.matches("\\d{4}-\\d{2}-\\d{2}")) {
				return true;
			} 
		}
		return false;
	}
}
