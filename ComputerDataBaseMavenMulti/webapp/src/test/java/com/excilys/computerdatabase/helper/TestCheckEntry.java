package com.excilys.computerdatabase.helper;


import junit.framework.TestCase;

public class TestCheckEntry extends TestCase {

	public void testChekIsIdLegal() {
		assertTrue(CheckEntry.checkIsId("65"));
	}
	public void testChekIsIdLegalButFobiden() {
		assertFalse(CheckEntry.checkIsId(""));
	}
	public void testChekIsIdIllegal() {
		assertFalse(CheckEntry.checkIsId("zqdqzdq  16azdqq"));
	}
	
	public void testChekIsDateLegal() {
		assertTrue(CheckEntry.checkIsDate("2002-10-22"));
	}
	public void testChekIsDateLegalButFobiden() {
		assertFalse(CheckEntry.checkIsDate("21/12/2002"));
	}
	public void testChekIsDatellegal() {
		assertFalse(CheckEntry.checkIsDate("<html>ici bas</html>"));
	}

}
