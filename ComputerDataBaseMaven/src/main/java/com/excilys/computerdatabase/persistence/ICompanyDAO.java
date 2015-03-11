package com.excilys.computerdatabase.persistence;

import java.util.List;

import com.excilys.computerdatabase.model.Company;

public interface ICompanyDAO {

	Company get(int id);

	List<Company> getAll();

	Company create(Company company);

	void update(Company company);

	void delete(Company company);

}