package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.util.List;

import com.excilys.computerdatabase.model.Company;

public interface ICompanyDAO {

	public abstract Company get(int id, Connection cnx);

	public abstract List<Company> getAll(Connection cnx);

	public abstract Company create(Company company, Connection cnx);

	public abstract void update(Company company, Connection cnx);

	public abstract void delete(Company company, Connection cnx);

}