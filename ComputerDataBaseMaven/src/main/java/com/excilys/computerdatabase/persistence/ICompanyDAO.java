package com.excilys.computerdatabase.persistence;

import java.util.List;

import com.excilys.computerdatabase.model.Company;

public interface ICompanyDAO {

	public abstract Company get(int id);

	public abstract List<Company> getAll();

	public abstract Company create(Company company);

	public abstract void update(Company company);

	public abstract void delete(Company company);

}