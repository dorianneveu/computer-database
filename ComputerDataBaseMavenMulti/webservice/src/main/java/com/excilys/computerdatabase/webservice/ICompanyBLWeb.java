package com.excilys.computerdatabase.webservice;

import java.util.List;

import com.excilys.computerdatabase.model.Company;

public interface ICompanyBLWeb {
	/**
	 * Delete in the database the company wich correspond to the id set and all the computer related to this company
	 * @param msg the id of the company
	 */
	public abstract void delete(int msg);
	/**
	 * Return the Company wich correspond to the id set
	 * @param id the id of the company
	 * @return 
	 */
	public abstract Company get(int id);
	/**
	 * Return the list of all the Company in the database
	 * @return
	 */
	public abstract List<Company> getAll();

}