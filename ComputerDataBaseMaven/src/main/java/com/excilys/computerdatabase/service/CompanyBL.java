package com.excilys.computerdatabase.service;

import java.sql.SQLException;
import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.CompanyDAO;
import com.excilys.computerdatabase.persistence.ComputerDAO;

public class CompanyBL extends AbstractBL<Company> {
	
	public CompanyBL() {
	}
	
	
	@Override
	public void deleteAbstract(Company company) {
		ComputerDAO.INSTANCE.deleteByCompany(company);
		CompanyDAO.INSTANCE.delete(company);
	}

	@Override
	public Company getAbstract(int id) throws SQLException {
		Company company = CompanyDAO.INSTANCE.get(id);
		return company;
	}

	@Override
	public List<Company> getAllAbstract() throws SQLException {
		List<Company> companies = CompanyDAO.INSTANCE.getAll();
		return companies;
	}

	@Override
	public void updateAbstract(Company object) throws SQLException {
		throw new UnsupportedOperationException("this method should not be used");
		
	}

	@Override
	public void insertAbstract(Company object) throws SQLException {
		throw new UnsupportedOperationException("this method should not be used");
		
	}

}
