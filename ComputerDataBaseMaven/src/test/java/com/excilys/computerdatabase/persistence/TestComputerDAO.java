package com.excilys.computerdatabase.persistence;

import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

public class TestComputerDAO  extends TestCase {
	
	ComputerDAO dao = new ComputerDAO();
	

//	public void testGetAll() {
//		List<Computer> computers = dao.getAll();
//		assertEquals(579, computers.size());
//	}
//	
//	public void testGetLegal(){
//		Computer computer = dao.get(5);
//		assertEquals("CM-5", computer.getName());
//	}
//	
//	public void testGetIllegal(){
//		Computer computer = dao.get(498949);
//		assertEquals(new Computer().getId(), computer.getId());
//	}
	
	public void testCrud(){
		Computer computer = new Computer(0, "Gadget", "1999-02-12", "2002-12-12", new Company(6));
		Computer computer2 = dao.create(computer);
		if (computer2.getId() < 1) {
			fail("Création Fail");
		} 
		computer2 = dao.get(computer2.getId());
		assertEquals(computer.getName(), computer2.getName());
		computer2 = new Computer(computer2.getId(), "TurboPoing", "1999-02-12", "2002-12-12", new Company(6));
//		computer2.setName("TurboPoing");
		dao.update(computer2);
		computer2 = dao.get(computer2.getId());
		assertEquals("TurboPoing", computer2.getName());
		if (dao.delete(computer2) < 1) {
			fail("Delete Fail");
		}
		
	}

}
