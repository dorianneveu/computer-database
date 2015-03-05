package com.excilys.computerdatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.CompanyDAO;
import com.excilys.computerdatabase.persistence.ComputerDAO;
import com.excilys.computerdatabase.persistence.ConnectionDAO;

public class CompanyBL extends AbstractBL<Company> {
	Connection cnx = null;
	
	public CompanyBL() {
	}
	
	
	@Override
	public void deleteAbstract(Company company, Connection cnx) {
		ComputerDAO.INSTANCE.deleteByCompany(company, cnx);
		CompanyDAO.INSTANCE.delete(company, cnx);
	}

	@Override
	public Company getAbstract(int id, Connection cnx) throws SQLException {
		Company company = CompanyDAO.INSTANCE.get(id, cnx);
		return company;
	}

	@Override
	public List<Company> getAllAbstract(Connection cnx) throws SQLException {
		cnx = getConnection();
		List<Company> companies = CompanyDAO.INSTANCE.getAll(cnx);
		ConnectionDAO.INSTANCE.close(cnx);
		return companies;
	}

	@Override
	public void updateAbstract(Company object, Connection cnx) throws SQLException {
		throw new UnsupportedOperationException("this method should not be used");
		
	}

	@Override
	public void insertAbstract(Company object, Connection cnx) throws SQLException {
		throw new UnsupportedOperationException("this method should not be used");
		
	}

}
