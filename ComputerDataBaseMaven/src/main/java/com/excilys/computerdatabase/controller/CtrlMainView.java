package com.excilys.computerdatabase.controller;

import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.CompanyDAO;

public class CtrlMainView {

	private CompanyDAO companyDAO;

	public CtrlMainView() {
		this.companyDAO = new CompanyDAO();
	}

	public List<Company> getAllCompany() {
		return companyDAO.getAll();
	}

}
