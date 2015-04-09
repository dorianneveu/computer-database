package com.excilys.computerdatabase.service;

import java.sql.SQLException;
import java.util.List;

import com.excilys.computerdatabase.model.Company;

public interface ICompanyBL {

	/**
	 * Delete in the database the company wich correspond to the id set and all the computer related to this company
	 * @param company
	 */
	public abstract void delete(Company company);

	public abstract Company get(int id) throws SQLException;

	public abstract List<Company> getAll() throws SQLException;

}