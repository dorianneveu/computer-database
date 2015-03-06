package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

public interface IComputerDAO {

	public abstract Computer get(int id);

	public abstract List<Computer> getAll();

	public abstract Computer create(Computer computer);

	public abstract int update(Computer computer);

	public abstract int delete(Computer computer);

	public abstract void deleteByCompany(Company company);

	public abstract long getCount();

	public abstract long getCountByName(String name);

	public abstract List<Computer> getAllLimit(int limit, int offset,
			String sort, String type);

	public abstract List<Computer> findByName(String name, int limit,
			int offset, String sort, String type);

}