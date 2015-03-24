package com.excilys.computerdatabase.service;

import java.sql.SQLException;
import java.util.List;

import com.excilys.computerdatabase.model.Company;

public interface ICompanyBL {

	public abstract void delete(Company company);

	public abstract Company get(int id) throws SQLException;

	public abstract List<Company> getAll() throws SQLException;

	public abstract void updateAbstract(Company object) throws SQLException;

	public abstract void insertAbstract(Company object) throws SQLException;

}