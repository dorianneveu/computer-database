package com.excilys.computerdatabase.controller;

import java.sql.SQLException;
import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.CompanyDAO;
import com.excilys.computerdatabase.persistence.ConnectionDAO;
import com.excilys.computerdatabase.persistence.ICompanyDAO;
import com.excilys.computerdatabase.service.CompanyBL;

public class CtrlMainView {

//	private ICompanyDAO companyDAO;
	private CompanyBL companyBL;

	public CtrlMainView() {
//		this.companyDAO = new CompanyDAO();
		this.companyBL = new CompanyBL();
	}

	public List<Company> getAllCompany() throws SQLException {
		return companyBL.getAll();
	}
	
	public int deleteCompany(String str) throws NumberFormatException, SQLException {
		companyBL.delete(companyBL.get(Integer.parseInt(str)));
		return 1;
	}

}
