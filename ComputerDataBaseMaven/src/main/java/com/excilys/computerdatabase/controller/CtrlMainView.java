package com.excilys.computerdatabase.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.service.CompanyBL;
@Component
public class CtrlMainView {
	@Autowired
	private CompanyBL companyBL;

	public CtrlMainView() {
	}

	public List<Company> getAllCompany() throws SQLException {
		return companyBL.getAll();
	}
	
	public int deleteCompany(String str) throws NumberFormatException, SQLException {
		companyBL.delete(companyBL.get(Integer.parseInt(str)));
		return 1;
	}

}
