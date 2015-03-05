package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import junit.framework.TestCase;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.CompanyMapper;
import com.excilys.computerdatabase.service.ComputerMapper;
import com.excilys.computerdatabase.service.dto.ComputerDTO;

public class TestComputerDAO  extends TestCase {
	
//	ComputerDAO dao = new ComputerDAO();

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
	
//	public void testCrud(){
//		Computer computer = new Computer(0, "Gadget", "1999-02-12", "2002-12-12", new Company(6));
//		Computer computer2 = dao.create(computer);
//		if (computer2.getId() < 1) {
//			fail("Création Fail");
//		} 
//		computer2 = dao.get(computer2.getId());
//		assertEquals(computer.getName(), computer2.getName());
//		computer2 = new Computer(computer2.getId(), "TurboPoing", "1999-02-12", "2002-12-12", new Company(6));
////		computer2.setName("TurboPoing");
//		dao.update(computer2);
//		computer2 = dao.get(computer2.getId());
//		assertEquals("TurboPoing", computer2.getName());
//		if (dao.delete(computer2) < 1) {
//			fail("Delete Fail");
//		}
//		
//	}
//	
//	public void testInsertMock(){ 
//		ComputerDAO computerDao = Mockito.mock(ComputerDAO.class);
//		Computer computer = new Computer();
//		computer.setName("deere");
//
//		Mockito.when(computerDao.create(Mockito.any(Computer.class))).thenAnswer(new Answer<Computer>() {
//			@Override
//			public Computer answer(InvocationOnMock invocation) throws Throwable {
//				Computer user = (Computer) invocation.getArguments()[0];
//				user.setId(1000);
//				return user;
//			}
//		});
//		assertEquals(0, computer.getId());
//		computer = computerDao.create(computer);
//		assertNotNull(computer.getId());
//		assertTrue(computer.getId() > 0);
//		assertTrue(computer.getId() == 1000);
//	}

}
