package com.excilys.computerdatabase.helper;

import java.sql.Timestamp;
import java.util.Date;

import junit.framework.TestCase;

public class TestDateConverter extends TestCase {

	public void testChekIsDateLegal() {
		assertSame(Date.class, DateConverter.stringToDate("2002-10-22").getClass());
	}
	public void testChekIsDateLegalButFobiden() {
		assertEquals(DateConverter.stringToDate("0000-00-00"), DateConverter.stringToDate(null));
	}
	
	public void testChekIsLocalDateLegal() {
//		assertSame(Timestamp.valueOf("2002-10-22 00:00:00").toString(), new Timestamp(DateConverter.stringToLocalDate("2002-10-22")));
	}
}
