package com.excilys.computerdatabase.model;

import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

@Entity
@Table(name="computer")
public class Computer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="introduced")
	@Type(type = "com.excilys.computerdatabase.util.CustomLocalDateTimeUserType")
	private LocalDate introduced;
	@Column(name="discontinued")
	@Type(type = "com.excilys.computerdatabase.util.CustomLocalDateTimeUserType")
	private LocalDate discontinued;
	@OneToOne
	private Company company;
	public Computer(){
		this.id = 0;
	}
	public Computer(String name) {
		this.id = 0;
		this.name = name;
	}
	public Computer(int id, String name, LocalDate introduced, LocalDate discontinued, Company company) {
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
	public LocalDate getIntroduced() {
		return introduced;
	}
	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}
	public void setIntroduced(Timestamp introduced) {
		this.introduced = (introduced != null && introduced.toString() != "0000-00-00 00:00:00") ? introduced.toLocalDateTime().toLocalDate() : null;
	}
	public LocalDate getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}
	public void setDiscontinued(Timestamp discontinued) {
		this.discontinued = (discontinued != null && discontinued.toString() != "0000-00-00 00:00:00") ? discontinued.toLocalDateTime().toLocalDate() : null;
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
	
	public boolean equals(Computer c){
		if (this.id == c.getId()) {
			return true;
		}
		if (this.name.equals(c.getName())) {
			if (this.company.equals((c.getCompany()))) {
				return true;
			}
			else{
				return false;
			}
		}else{
			return false;
		}
	}
	
}
