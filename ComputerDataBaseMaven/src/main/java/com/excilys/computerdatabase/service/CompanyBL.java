package com.excilys.computerdatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.CompanyDAO;
import com.excilys.computerdatabase.persistence.ComputerDAO;
import com.excilys.computerdatabase.persistence.ConnectionDAO;

public class CompanyBL {
	CompanyDAO dao;
	
	public CompanyBL() {
		dao = new CompanyDAO();
	}

	public List<Company> getAllCompany() {
		return dao.getAll();
	}
	
	public void delete(Company company) {
		Connection cnx = null;
		try {
			cnx = ConnectionDAO.INSTANCE.connectionPool.getConnection();
			cnx.setAutoCommit(false);
			CompanyDAO companyDAO = new CompanyDAO(cnx);
			ComputerDAO computerDAO = new ComputerDAO(cnx);
			computerDAO.deleteByCompany(company);
			companyDAO.delete(company);	
			cnx.commit();
		} catch (SQLException e) {
			try {
				cnx.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {

			try {
				cnx.setAutoCommit(true);
				cnx.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
