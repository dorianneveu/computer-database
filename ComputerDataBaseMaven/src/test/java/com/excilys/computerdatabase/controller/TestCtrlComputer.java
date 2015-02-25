package com.excilys.computerdatabase.controller;

import java.util.List;

import com.excilys.computerdatabase.controller.CtrlComputerView;
import com.excilys.computerdatabase.model.Computer;

import junit.framework.TestCase;

public class TestCtrlComputer extends TestCase {

	public void testGetAll() {
		CtrlComputerView ctrl = new CtrlComputerView();
		List<Computer> computers = ctrl.getAllComputer();
		assertEquals(593, computers.size());
	}
	
	

}
