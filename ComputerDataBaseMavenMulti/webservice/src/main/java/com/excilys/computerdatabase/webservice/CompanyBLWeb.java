package com.excilys.computerdatabase.webservice;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.ICompanyDAO;
import com.excilys.computerdatabase.persistence.IComputerDAO;

@Path("/company")
public class CompanyBLWeb implements ICompanyBLWeb{
	
	@Autowired
	private ICompanyDAO companyDAO;
	@Autowired
	private IComputerDAO computerDAO;
	
	public CompanyBLWeb() {
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.webservice.ICompanyBL#delete(com.excilys.computerdatabase.model.Company)
	 */
	@Override
	@DELETE
	@Path("/{param}")
	public void delete(@PathParam("param") int msg) {
		Company company = companyDAO.get(msg);
		computerDAO.deleteByCompany(company);
		companyDAO.delete(company);
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.webservice.ICompanyBL#get(int)
	 */
	@Override
	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Company get(@PathParam("param") int id)  {
		Company company = companyDAO.get(id);
		return company;
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.webservice.ICompanyBL#getAll()
	 */
	@Override
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Company> getAll() {
		List<Company> companies = companyDAO.getAll();
		return companies;
	}
}
