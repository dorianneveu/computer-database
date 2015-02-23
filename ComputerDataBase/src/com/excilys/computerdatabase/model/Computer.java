package com.excilys.computerdatabase.model;

public class Computer {

	private int id;
	private String name;
	private String introduced;
	private String discontinued;
	private Company company;
	public Computer(){
		this.id = 0;
	}
	public Computer(String name) {
		this.id = 0;
		this.name = name;
	}
	public Computer(int id, String name, String introduced, String discontinued, Company company) {
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
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id : ");
		sb.append(this.id);
		sb.append(" name : ");
		sb.append(this.name);
		sb.append(" introduced : ");
		sb.append(this.introduced);
		sb.append(" discontinued : ");
		sb.append(this.discontinued);
		sb.append(" company : ");
		sb.append(this.company.getName());
		return sb.toString();
	}
	
	public String toStringLittle() {
		StringBuilder sb = new StringBuilder();
		sb.append("id : ");
		sb.append(this.id);
		sb.append(" name : ");
		sb.append(this.name);
		return sb.toString();
	}
	
}
