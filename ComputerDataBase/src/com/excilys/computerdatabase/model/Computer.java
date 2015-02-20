package com.excilys.computerdatabase.model;

import java.security.Timestamp;
import java.sql.Date;

public class Computer {

	private int id;
	private String name;
	private String introduced;
	private String discontinued;
	private Company company;
	public Computer(){
		this.id = 0;
	}
	public Computer(String name){
		this.id = 0;
		this.name = name;
	}
	public Computer(int id, String name, String introduced, String discontinued, Company company){
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduced() {
		return introduced;
	}
	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}
	public String getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public String toString(){
		return "id : "+ this.id +" name : " +this.name+" introduced : "+this.introduced+" discontinued : "+ this.discontinued+
				" company : "+this.company.getName();
	}
	
}
