package com.excilys.computerdatabase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.ICompanyDAO;
import com.excilys.computerdatabase.persistence.IComputerDAO;

@Service
public class CompanyBL implements ICompanyBL{
	
	@Autowired
	private ICompanyDAO companyDAO;
	@Autowired
	private IComputerDAO computerDAO;
	
	public CompanyBL() {
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.service.ICompanyBL#deleteAbstract(com.excilys.computerdatabase.model.Company)
	 */
	@Override
	public void delete(Company company) {
		computerDAO.deleteByCompany(company);
		companyDAO.delete(company);
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.service.ICompanyBL#getAbstract(int)
	 */
	@Override
	public Company get(int id)  {
		Company company = companyDAO.get(id); 
		return company;
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.service.ICompanyBL#getAllAbstract()
	 */
	@Override
	public List<Company> getAll() {
		List<Company> companies = companyDAO.getAll();
		return companies;
	}

}
