package com.excilys.computerdatabase.persistence;

import java.util.List;


import com.excilys.computerdatabase.model.Company;

import junit.framework.TestCase;

public class TestCompanyDAO extends TestCase {
	
	CompanyDAO dao = new CompanyDAO();
	

	public void testGetAll() {
		List<Company> companies = dao.getAll();
		assertEquals(46, companies.size());
	}
	
	public void testGetLegal(){
		Company company = dao.get(5);
		assertEquals("Tandy Corporation", company.getName());
	}
	
	public void testGetIllegal(){
		Company company = dao.get(498949);
		assertEquals(new Company().getId(), company.getId());
	}
}
