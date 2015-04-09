package com.excilys.computerdatabase.clictrl;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.service.CompanyBL;
@Component
public class CtrlMainView {
	@Autowired
	private CompanyBL companyBL;
	
	Client client;
	WebTarget companyTarget;

	public CtrlMainView() {
		client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
		companyTarget = client.target("http://localhost:8080/webservice/rest/company");
	}

	public List<Company> getAllCompany()  {
		List<Company> companies = new ArrayList<Company>();
		companies = companyTarget.path("/all").request(MediaType.APPLICATION_JSON).get(new GenericType<List<Company>>() {});
		
		return companies;
	}
	
	public void deleteCompany(String str) {
		companyTarget.path("/" + str).request(MediaType.APPLICATION_JSON).delete();
	}

}
