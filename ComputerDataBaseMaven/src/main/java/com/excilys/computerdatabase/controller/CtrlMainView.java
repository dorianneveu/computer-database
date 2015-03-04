package com.excilys.computerdatabase.controller;

import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.CompanyDAO;
import com.excilys.computerdatabase.service.CompanyBL;

public class CtrlMainView {

	private CompanyDAO companyDAO;
	private CompanyBL companyBL;

	public CtrlMainView() {
		this.companyDAO = new CompanyDAO();
		this.companyBL = new CompanyBL();
	}

	public List<Company> getAllCompany() {
		return companyDAO.getAll();
	}
	
	public int deleteCompany(String str) {
		companyBL.delete(companyDAO.get(Integer.parseInt(str)));
		return 1;
	}

}
