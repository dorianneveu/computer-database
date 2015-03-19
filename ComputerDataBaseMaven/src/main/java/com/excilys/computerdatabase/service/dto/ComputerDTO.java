package com.excilys.computerdatabase.service.dto;


import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import com.excilys.computerdatabase.controller.validator.Date;

public class ComputerDTO {
	
	private int id;
	@NotEmpty
	private String name;
	@Date(message = "{dashboard.introduced}")
	private String introduced;
	@Date(message = "{dashboard.discontinued}")
	private String discontinued;
	private int companyId;
	private String companyName;
	
	public ComputerDTO(){
		this.id = 0;
	}
	public ComputerDTO(String name) {
		this.id = 0;
		this.name = name;
	}
	public ComputerDTO(int id, String name, String introduced, String discontinued, int companyId, String companyName) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
		this.companyName = companyName;
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
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
		sb.append(" companyId : ");
		sb.append(this.companyId);
		sb.append(" company : ");
		sb.append(this.companyName);
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
	
//	public boolean equals(Computer c){
//		if (this.ge(c)) {
//			return true;
//		}
//		if (this.id == c.getId()) {
//			return true;
//		}
//		if (this.name.equals(c.getName())) {
//			if (this.company.equals((c.getCompany()))) {
//				return true;
//			}
//			else{
//				return false;
//			}
//		}else{
//			return false;
//		}
//	}

}
