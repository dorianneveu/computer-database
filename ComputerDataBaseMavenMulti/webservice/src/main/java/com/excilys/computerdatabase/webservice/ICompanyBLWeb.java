package com.excilys.computerdatabase.webservice;

import java.util.List;

import javax.ws.rs.core.Response;

import com.excilys.computerdatabase.model.Company;

public interface ICompanyBLWeb {

	public abstract Response delete(String msg, Company company);

	public abstract Company get(int id);

	public abstract List<Company> getAll();

}